package com.brainburns.brainburns.web.rest;

import com.brainburns.brainburns.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Arthan on 24.12.2016. Project: brainburns
 */

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public String registerUser(@RequestParam Map<String, String> request, ModelMap modelMap) {
        String username = request.get("username");
        String password = request.get("password");
        String repeatedPassword = request.get("repeatedPassword");

        if (!repeatedPassword.equals(password)) {
            modelMap.put("passwordNotRepeated", true);
            return "redirect:/signup";
        }

        modelMap.put("exists", true);
        return "redirect:/signup";
    }
}
