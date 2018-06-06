package votesystem.web.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import votesystem.AuthorizedUser;
import votesystem.model.User;
import votesystem.to.UserTo;

@RestController
@RequestMapping(ProfileRestController.REST_URL)
public class ProfileRestController extends AbstractUserController {
    static final String REST_URL = "/rest/profile";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get() {
        return super.get(AuthorizedUser.id());
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete() {
        super.delete(AuthorizedUser.id());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody User user, @PathVariable("id") int id) {
        super.update(user, AuthorizedUser.id());
    }

//    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void update(@RequestBody UserTo userTo) {
//        super.update(userTo, AuthorizedUser.id());
//    }
}
