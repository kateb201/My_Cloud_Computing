package demo;

import java.util.List;

public interface UserService {

    UserBoundaryEnc createUser(UserBoundary user);

    UserBoundaryEnc login(String userEmail);

    List<UserBoundaryEnc> getAllUsersByYear(String year,
            int size, int page);

    // UserBoundary login(String userSpace, String userEmail);

    // UserBoundary updateUser(String userSpace, String userEmail, UserBoundary
    // update);

    // List<UserBoundary> getAllUsers(String adminSpace, String adminEmail);

    // void deleteAllUsers(String adminSpace, String adminEmail);

}
