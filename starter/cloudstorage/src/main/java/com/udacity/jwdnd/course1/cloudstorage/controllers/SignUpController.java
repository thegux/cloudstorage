package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    private UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getSignup() {
        return "signup";
    }

    @PostMapping()
    public RedirectView signupUser(@ModelAttribute User user, RedirectAttributes redirectAttributes, Model model) {
        String signUpError = null;

        if(!userService.isUsernameAvailable(user.getUsername())) {
            signUpError = "Username not available";
        }

        if(signUpError == null) {
            int rowsAdded = userService.createUser(user);
            if(rowsAdded < 0) {
                signUpError = "There was an error. Please try again.";
            }
        }


        if (signUpError == null) {
            redirectAttributes.addFlashAttribute("success", true);
            RedirectView redirectView = new RedirectView("/login", true);
            return redirectView;
        } else {
            RedirectView redirectView = new RedirectView("/signup", true);
            redirectAttributes.addFlashAttribute("success", false);
            redirectAttributes.addFlashAttribute("errorMessage", signUpError);
            return redirectView;
        }

    }
}
