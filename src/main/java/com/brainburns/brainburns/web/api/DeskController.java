package com.brainburns.brainburns.web.api;

import com.brainburns.brainburns.domain.model.Desk;
import com.brainburns.brainburns.service.DeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> createDesk(@RequestBody Desk desk, Authentication auth) {

        desk.setUsername(auth.getName());
        long createdDeskId = deskService.createDesk(desk);

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> responseData = new HashMap<>();
        response.put("data", responseData);

        if (createdDeskId != -1) {
            responseData.put("status", "success");
        } else {
            responseData.put("status", "error");
        }

        return response;
    }

    @GetMapping("/test")
    public String test() {
        return "Tested";
    }
}
