package com.brainburns.brainburns.web.rest;

import com.brainburns.brainburns.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Arthan on 24.12.2016. Project: brainburns
 */

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "user/{username}", method = RequestMethod.GET)
    public String helloUser(@PathVariable("username") String username) {
        String greeting = userService.getGreeting(username);
        return greeting;
    }
}
