package CookItUpWeb.home;

import CookItUpWeb.auxiliary.StringAuxiliary;
import CookItUpWeb.data.user.User;
import CookItUpWeb.data.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path="/home")
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping()
    public String home() {
        return "forward:/home.html";
    }

    @RequestMapping(path="log_in")
    public @ResponseBody String logIn(HttpSession session, @RequestParam String name, @RequestParam String password ) {
        String result = null;
        if (StringAuxiliary.isEmpty(name) || StringAuxiliary.isEmpty(password)) {
            result = "result:/error/form_incomplete";
        }
        else {
            for (User user : userRepository.findAll()) {
                if (name.equalsIgnoreCase(user.getName())) {
                    if (password.equals(user.getPassword())) {
                        session.setAttribute("user", user);
                        result = "";
                        break;
                    } else {
                        result = "result:/error/wrong_password";
                        break;
                    }
                }
            }
            if (result == null)
                result = "result:/error/user_not_found";
        }
        return result;
    }

    @RequestMapping(path="sign_up")
    public @ResponseBody String signUp(HttpServletRequest request, HttpSession session, @RequestParam String name, @RequestParam String password ) {
        if (StringAuxiliary.isEmpty(name) || StringAuxiliary.isEmpty(password)) {
            return "The submitted form is incomplete";
        }
        for (User user : userRepository.findAll()) {
            if (name.equalsIgnoreCase(user.getName())) {
                return "The user name you are trying to get is already assigned";
            }
        }
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setBlocked(false);
        user.setChips(0);
        userRepository.save(user);
        session.setAttribute("user", user);
        return "";
    }

    //TODO change this to an html that shows all recipes
    @RequestMapping(path="enter")
    public @ResponseBody String enter() {
        return "Hello";
    }
}