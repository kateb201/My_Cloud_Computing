package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImplementation implements UserService {

    private UserHandler serviceHandler;
    private final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);
    public final Pattern VALID_PASSWORD_REGEX = Pattern.compile("^(?=.*\\d).+$");
    public final Pattern VALID_BIRTHDATE_REGEX = Pattern.compile("^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$");

    @Autowired
    public UserServiceImplementation(UserHandler serviceHandler) {
        this.serviceHandler = serviceHandler;
    }

    @Override
    @Transactional
    public UserBoundary createUser(UserBoundary user) {
        if (user == null) {
            throw new RuntimeException("User must not be null");
        }
        if (assertUser(user)) {
            UserEntity userEntity = convertToEntity(user);
            if (!checkDup(userEntity)) {
                serviceHandler.save(userEntity);
                UserBoundary userBoundary = convertToBoundary(userEntity);
                return userBoundary;
            }
        }
        throw new RuntimeException("Cannot create user, check all attributes are correct");
    }

    private boolean checkDup(UserEntity userEntity) {
        Optional<UserEntity> existing = serviceHandler.findById(userEntity.getEmail());
        return existing.isPresent();
    }

    private boolean assertUser(UserBoundary user) {
        if (user.getName().getFirst().equals("") || user.getName().getLast().equals("")) {
            throw new RuntimeException("User name not valid");
        }
        if (!assertEmail(user.getEmail())) {
            throw new RuntimeException("Email not valid");
        }
        if (user.getPassword().length() < 5 && !assertPassword(user.getPassword())) {
            throw new RuntimeException("Password not valid");
        }
        if (!assertBirthdate(user.getBirthdate())) {
            throw new RuntimeException("Birthdate not valid");
        }
        if (user.getRoles().equals("")) {
            throw new RuntimeException("Role cant be null");
        }
        return true;
    }

    private boolean assertEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    private boolean assertPassword(String password) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
        return matcher.find();
    }

    private boolean assertBirthdate(String birthdate) {
        Matcher matcher = VALID_BIRTHDATE_REGEX.matcher(birthdate);
        return matcher.find();
    }

    @Override
    @Transactional(readOnly = true)
    public UserBoundary login(String userEmail) {
        Optional<UserEntity> existing = serviceHandler.findById(userEmail);
        if (existing.isPresent()) {
            UserEntity entity = existing.get();
            return this.convertToBoundary(entity);
        } else {
            throw new RuntimeException("message could not be found");
        }
    }

    private UserEntity convertToEntity(UserBoundary boundary) {
        return new UserEntity(boundary.getName().getFirst(),
                boundary.getName().getLast(),
                boundary.getEmail(), boundary.getPassword(),
                boundary.getBirthdate(), boundary.getRoles());
    }

    private UserBoundary convertToBoundary(UserEntity entity) {
        return new UserBoundary(entity.getEmail(),
                new Name(entity.getFirst(),
                        entity.getLast()),
                entity.getPassword(),
                entity.getBirthdate(), entity.getRoles());
    }

}
