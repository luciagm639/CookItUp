//TODO delete
package CookItUpWeb.data.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path="add")
    public @ResponseBody String add(@RequestParam String name, @RequestParam String password ) {
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

    @GetMapping(path="all")
    public @ResponseBody List<User> allUsers() {
        LinkedList<User> list = new LinkedList<>();
        for (User u : userRepository.findAll()) {
            list.add(u);
        }
        return list;
    }

}
