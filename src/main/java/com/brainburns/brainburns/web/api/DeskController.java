package com.brainburns.brainburns.web.api;

import com.brainburns.brainburns.domain.model.Desk;
import org.springframework.http.MediaType;
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, List<Desk>> getDesks() {

        List<Desk> deskList = new ArrayList<>();
        deskList.add(new Desk(1, "English"));
        deskList.add(new Desk(2, "Kanji"));
        deskList.add(new Desk(3, "Word"));

        Map<String, List<Desk>> result = new HashMap<>();
        result.put("data", deskList);

        return result;
    }
}
