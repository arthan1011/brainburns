package com.brainburns.brainburns.web.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Arthan on 24.12.2016. Project: brainburns
 */

@Controller
public class PageController {

    @GetMapping("/")
    public String getMainPage(ModelMap modelMap) {
        return "index";
    }

    @GetMapping("/heroes")
    public String getHeroesPage(ModelMap modelMap) {
        return "heroes";
    }

    @GetMapping("/signin")
    public String getSignInPage(ModelMap modelMap) {
        return "login";
    }

    @GetMapping("/signup")
    public String getSignUpPage(ModelMap modelMap) {
        return "signup";
    }
}
