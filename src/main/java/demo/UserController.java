package demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/customers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserBoundaryEnc createNewUser(@RequestBody UserBoundary input) {
        return userService.createUser(input);
    }

    @RequestMapping(path = "/customers/{userEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserBoundaryEnc login(
            @PathVariable("userEmail") String email) {
        return userService.login(email);
    }

    @RequestMapping(path = "/customers/search?criteriaType=byBirthYear&criteriaValue={value}&size={size}&page={page}&sortBy={sortAttribute}&sortOrder={order}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserBoundary[] getAllUsersByYear(
            @RequestParam(name = "value", required = false, defaultValue = "2020") String year,
            @RequestParam(name = "size", required = false, defaultValue = "20") int size,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "sortAttribute", required = false, defaultValue = "email") String sortAttribute,
            @RequestParam(name = "order", required = false, defaultValue = "ASC") String order) {
        // get all Items

        List<UserBoundaryEnc> boundaries = this.userService.getAllUsersByYear(year, size, page);

        return boundaries
                .toArray(new UserBoundary[0]);
    }

}
