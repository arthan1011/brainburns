package com.brainburns.brainburns.service;

import org.springframework.stereotype.Component;

/**
 * Created by Arthan on 23.12.2016. Project: brainburns
 */

@Component
public class UserService {

    public String getGreeting(String name) {
        return "Greetings, " + name;
    }
}
