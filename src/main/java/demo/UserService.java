package demo;

//import java.util.List;

public interface UserService {

    UserBoundary createUser(UserBoundary user);

    UserBoundary login(String userEmail);

    // UserBoundary login(String userSpace, String userEmail);

    // UserBoundary updateUser(String userSpace, String userEmail, UserBoundary
    // update);

    // List<UserBoundary> getAllUsers(String adminSpace, String adminEmail);

    // void deleteAllUsers(String adminSpace, String adminEmail);

}