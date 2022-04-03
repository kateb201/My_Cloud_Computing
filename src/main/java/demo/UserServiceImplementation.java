package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImplementation implements UserService {

    private UserHandler serviceHandler;
    private final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);

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
        if (user.getUsername().getFirstname().equals("") || user.getUsername().getLastname().equals("")) {
            throw new RuntimeException("User name not valid");
        }
        if (!assertEmail(user.getEmail())) {
            throw new RuntimeException("Email not valid");
        }
        if (user.getPassword().equals("") || user.getBirthday().equals("")) {
            throw new RuntimeException("Avatar not valid");
        }
        if (user.getRole() == null) {
            throw new RuntimeException("Role cant be null");
        }
        return true;
    }

    private boolean assertEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
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
        return new UserEntity(boundary.getUsername().getFirstname(),
                boundary.getUsername().getLastname(),
                boundary.getEmail(), boundary.getPassword(),
                boundary.getBirthday(), boundary.getRole());
    }

    private UserBoundary convertToBoundary(UserEntity entity) {
        return new UserBoundary(new UserName(entity.getFirst_name(),
                entity.getLast_name()),
                entity.getEmail(), entity.getPassword(),
                entity.getBirthday(), entity.getRole());
    }

}
