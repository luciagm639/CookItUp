//TODO
package CookItUpWeb.data.administrator;

import CookItUpWeb.auxiliary.StringAuxiliary;
import CookItUpWeb.data.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path="admin")

public class AdministratorController {
    @Autowired private AdministratorRepository administratorRepository;

    @RequestMapping(path= "current")
    public @ResponseBody Administrator getCurrentAdmin(HttpSession session) {
        Administrator admin = null;

        if (session.getAttribute("admin") instanceof Administrator) {
            session.getAttribute("admin");
            admin = (Administrator) session.getAttribute("admin");
        }
        return admin;
    }

    @RequestMapping(path="log_in")
    public ModelAndView logIn(HttpServletRequest request, HttpSession session, @RequestParam String name, @RequestParam String password ) {
        request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
        String redirect = null;
        if (StringAuxiliary.isEmpty(name) || StringAuxiliary.isEmpty(password)) {
            redirect = "redirect:/error/form_incomplete";
        }
        else {
            for (Administrator admin : administratorRepository.findAll()) {
                if (name.equalsIgnoreCase(admin.getName())) {
                    if (password.equals(admin.getPassword())) {
                        session.setAttribute("admin", admin);
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
        for (Administrator admin : administratorRepository.findAll()) {
            if (name.equalsIgnoreCase(admin.getName())) {
                return new ModelAndView("redirect:/error/username_not_available");
            }
        }
        Administrator admin = new Administrator();
        admin.setName(name);
        admin.setPassword(password);
        administratorRepository.save(admin);
        session.setAttribute("admin", admin);
        return new ModelAndView("redirect:/home/enter");
    }


}
