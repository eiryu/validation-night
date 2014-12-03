package controller;

import form.UserForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("")
    public String index(UserForm userForm) {
        return "index";
    }

    @RequestMapping("register")
    public String register(@Valid UserForm userForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            LOGGER.warn("bindingResult: " + bindingResult);
            return "index";
        }
        return "redirect:/complete";
    }

    @RequestMapping("complete")
    public String complete() {
        return "complete";
    }
}
