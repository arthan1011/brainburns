package com.brainburns.brainburns.web.api;

import com.brainburns.brainburns.domain.model.Desk;
import com.brainburns.brainburns.service.DeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
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
    public ResponseEntity<Map<String, Object>> createDesk(@RequestBody Desk desk, Authentication auth) {
        desk.setUsername(auth.getName());
        long createdDeskId = deskService.createDesk(desk);

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("status", "success");
        responseData.put("diskId", createdDeskId);

        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/test")
    public String test() {
        return "Tested";
    }
}
