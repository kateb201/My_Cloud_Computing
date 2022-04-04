package demo;

public interface UserService {

    UserBoundaryEnc createUser(UserBoundary user);

    UserBoundaryEnc login(String userEmail);

    Object getAllUsersByAttr(String atrr, int size, int page);

    // UserBoundary login(String userSpace, String userEmail);

    // UserBoundary updateUser(String userSpace, String userEmail, UserBoundary
    // update);

    // void deleteAllUsers(String adminSpace, String adminEmail);

}
