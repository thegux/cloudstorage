package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.forms.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.helpers.AuthHelper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    private final NoteService noteService;
    private final AuthHelper authHelper;
    private final UserService userService;

    public HomeController(NoteService noteService, AuthHelper authHelper, UserService userService) {
        this.noteService = noteService;
        this.authHelper = authHelper;
        this.userService = userService;
    }

    @GetMapping("/home")
    public String getHome(Model model) {
        User authUser = authHelper.getAuthUser(userService);
        if(authUser != null) {
            model.addAttribute("notes", this.noteService.getNoteList(authUser.getUserid()));
        }
        return "home";
    }



}
