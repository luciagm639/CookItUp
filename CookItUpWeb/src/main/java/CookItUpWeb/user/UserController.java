package CookItUpWeb.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/user")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path="add")
    public @ResponseBody String signUp(@RequestParam String name, @RequestParam String password ) {
        for (User u : userRepository.findAll()) {
            if (u.getName().equalsIgnoreCase(name)) {
                return "Name is already in use";
            }
        }
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setBlocked(false);
        user.setChips(0);
        userRepository.save(user);
        return user + " has been added";
    }

    @RequestMapping(path="delete")
    public @ResponseBody String delete(@RequestParam String name, @RequestParam String password) {
        for (User u : userRepository.findAll()) {
            if (u.getName().equalsIgnoreCase(name)) {
                if (u.getPassword().equals(password)) {
                    userRepository.delete(u);
                    return u + " has been deleted";
                }
                else {
                    return "Wrong password";
                }
            }
        }
        return "User not found";
    }

}
