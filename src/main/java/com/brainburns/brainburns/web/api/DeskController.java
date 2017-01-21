package com.brainburns.brainburns.web.api;

import com.brainburns.brainburns.domain.model.Desk;
import com.brainburns.brainburns.service.DeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by arthan on 19.01.2017. | Project brainburns
 */

@RestController
@RequestMapping("/api/desk")
public class DeskController {

    private final DeskService deskService;

    @Autowired
    public DeskController(DeskService deskService) {
        this.deskService = deskService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, List<Desk>> getDesks(Authentication auth) {
        String username = auth.getName();
        List<Desk> deskList = deskService.findByUsername(username);

        Map<String, List<Desk>> result = new HashMap<>();
        result.put("data", deskList);

        return result;
    }

    @GetMapping("/test")
    public String test() {
        return "Tested";
    }
}
