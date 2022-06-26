package CookItUpWeb.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/error")
public class ErrorController {

    //TODO try ErrorPage
    @RequestMapping(path="default")
    public @ResponseBody String defaultError() {
        return "There has been an error";
    }

    @RequestMapping(path="user_not_found")
    public @ResponseBody String userNotFound() {
        return "The user was not found";
    }

    @RequestMapping(path="wrong_password")
    public @ResponseBody String wrongPassword() {
        return "The password is wrong";
    }

    @RequestMapping(path="username_not_available")
    public @ResponseBody String usernameUnavailable() {
        return "The user name you are trying to get is already assigned";
    }
    @RequestMapping(path="form_incomplete")
    public @ResponseBody String formIncomplete() {
        return "The form you have submitted has some obligatory fields left empty";
    }
}
