package com.brainburns.brainburns.web.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Arthan on 24.12.2016. Project: brainburns
 */

@Controller
public class PageController {

    @GetMapping("/*")
    public String getMainPage(ModelMap modelMap) {
        return "index";
    }

    @GetMapping("/main")
    public String getMain(ModelMap modelMap) {
        return "main";
    }

    @GetMapping("/signin")
    public String getSignInPage(ModelMap modelMap) {
        return "signin";
    }

    @GetMapping("/signup")
    public String getSignUpPage(ModelMap modelMap) {
        return "signup";
    }
}
