package ai.prama.services.user.endpoints;

import ai.prama.model.user.User;
import ai.prama.services.user.exceptions.BadSearchRequestException;
import ai.prama.services.user.services.api.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class UserRestControllerV1 extends UserDomainV1 {

    private final UserService userService;

    public UserRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody User user) {
        userService.addNew(user);
    }

    @GetMapping(value = "/search", produces = APPLICATION_JSON_VALUE)
    public User searchUserByUsername(@RequestParam(required = false) String username,
                                     @RequestParam(required = false) String email) {
        if (StringUtils.hasText(username)) {
            return userService.getUserByUsername(username);
        } else {
            if (StringUtils.hasText(email)) {
                return userService.getUserByEmail(email);
            } else {
                throw new BadSearchRequestException("No search params passed");
            }
        }
    }

    @GetMapping(value = "/{userId}", produces = APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

}
