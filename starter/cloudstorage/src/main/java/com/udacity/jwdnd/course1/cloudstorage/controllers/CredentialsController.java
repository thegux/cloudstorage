package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.forms.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.helpers.AuthHelper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import netscape.javascript.JSObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CredentialsController {

    private CredentialService credentialService;
    private final AuthHelper authHelper;
    private final UserService userService;

    private final EncryptionService encryptionService;

    public CredentialsController(CredentialService credentialService, AuthHelper authHelper, UserService userService, EncryptionService encryptionService) {
        this.credentialService = credentialService;
        this.authHelper = authHelper;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }

    @PostMapping("/addCredential")
    public String addCredentials(CredentialForm credentialForm, Model model) {
        User authUser = authHelper.getAuthUser(userService);
        String encodedKey = encryptionService.getKey();

        String encryptedPassword = encryptionService.encryptValue(credentialForm.getPassword(), encodedKey);
        Credential credential = new Credential(null, credentialForm.getUrl(), credentialForm.getUsername(), encodedKey, encryptedPassword, authUser.getUserid());

        credentialService.addCredential(credential);
        model.addAttribute("credentials", credentialService.getCredentials(authUser.getUserid()));
        return "home";
    }

    @PostMapping("/updateCredential")
    public String updateCredential(@RequestParam("credentialid") Integer credentialid, CredentialForm credentialForm, Model model) {
        User authUser = authHelper.getAuthUser(userService);
        String encodedKey = encryptionService.getKey();

        String encryptedPassword = encryptionService.encryptValue(credentialForm.getPassword(), encodedKey);
        Credential credential = new Credential(credentialid, credentialForm.getUrl(), credentialForm.getUsername(), encodedKey, encryptedPassword, authUser.getUserid());

        credentialService.updateCredential(credential);
        model.addAttribute("credentials", credentialService.getCredentials(authUser.getUserid()));
        return "home";
    }


    @GetMapping("/removeCredential")
    public String removeCredential(@RequestParam("credentialid") Integer credentialid, Model model) {
        User authUser = authHelper.getAuthUser(userService);
        credentialService.deleteCredential(credentialid);
        model.addAttribute("credentials", credentialService.getCredentials(authUser.getUserid()));

        return "home";
    }


    @GetMapping(value = "/getDecryptedCredential", produces = "application/json")
    public @ResponseBody Map getDecryptedCredential(@RequestParam("credentialid") Integer credentialid, Model model) {
        User authUser = authHelper.getAuthUser(userService);
        Credential credential = credentialService.getCredential(credentialid, authUser.getUserid());

        String decryptedPassword = encryptionService.decryptValue(credential.getPassword(), credential.getKey());
        Map<String, String> responseMap = new HashMap<>();

        responseMap.put("success", "true");
        responseMap.put("decryptedPassword", decryptedPassword);

        return  responseMap;

    }

}

