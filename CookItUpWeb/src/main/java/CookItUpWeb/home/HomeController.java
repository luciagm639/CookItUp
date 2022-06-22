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

    @RequestMapping(path="log_in")
    public ModelAndView logIn(HttpServletRequest request, HttpSession session, @RequestParam String name, @RequestParam String password ) {
        request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
        String redirect = null;
        if (StringAuxiliary.isEmpty(name) || StringAuxiliary.isEmpty(password)) {
            redirect = "redirect:/error/form_incomplete";
        }
        else {
            for (User user : userRepository.findAll()) {
                if (name.equalsIgnoreCase(user.getName())) {
                    if (password.equals(user.getPassword())) {
                        session.setAttribute("user", user);
                        redirect = "redirect:/home/enter";
                        break;
                    } else {
                        redirect = "redirect:/error/wrong_password";
                        break;
                    }
                }
            }
            if (redirect == null)
                redirect = "redirect:/error/user_not_found";
        }
        return new ModelAndView(redirect);
    }

    @RequestMapping(path="sign_up")
    public ModelAndView signUp(HttpServletRequest request, HttpSession session, @RequestParam String name, @RequestParam String password ) {
        request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
        if (StringAuxiliary.isEmpty(name) || StringAuxiliary.isEmpty(password)) {
            return new ModelAndView("redirect:/error/form_incomplete");
        }
        for (User user : userRepository.findAll()) {
            if (name.equalsIgnoreCase(user.getName())) {
                return new ModelAndView("redirect:/error/username_not_available");
            }
        }
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setBlocked(false);
        user.setChips(0);
        userRepository.save(user);
        session.setAttribute("user", user);
        return new ModelAndView("redirect:/home/enter");
    }

    //TODO change this to an html that shows all recipes
    @RequestMapping(path="enter")
    public @ResponseBody String enter() {
        return "Hello";
    }
}