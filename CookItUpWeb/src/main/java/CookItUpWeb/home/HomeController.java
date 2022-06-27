package CookItUpWeb.home;

import CookItUpWeb.auxiliary.CopyFolder;
import CookItUpWeb.auxiliary.StringAuxiliary;
import CookItUpWeb.data.user.User;
import CookItUpWeb.data.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping(path="/home")
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path="log_in")
    public @ResponseBody String logIn(HttpSession session, @RequestParam String name, @RequestParam String password ) {
        String result = null;
        if (StringAuxiliary.isEmpty(name) || StringAuxiliary.isEmpty(password)) {
            result = "The submitted form is incomplete";
        }
        else {
            for (User user : userRepository.findAll()) {
                if (name.equalsIgnoreCase(user.getName())) {
                    if (password.equals(user.getPassword())) {
                        session.setAttribute("user", user);
                        session.setAttribute("spent_chips", 0);
                        result = "";
                        break;
                    } else {
                        result = "The password is wrong";
                        break;
                    }
                }
            }
            if (result == null)
                result = "User not found";
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
        System.out.println(user.getId());
        try {
            System.out.println(user.getId());
            CopyFolder.copyFolder("user\\0", "user\\"+user.getId());
        } catch (IOException e) {
            e.printStackTrace();
            userRepository.delete(user);
            user.setId(-1);

        }
        session.setAttribute("user", user);
        return "";
    }
}