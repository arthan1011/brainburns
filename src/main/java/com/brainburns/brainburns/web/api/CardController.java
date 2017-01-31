package com.brainburns.brainburns.web.api;

import com.brainburns.brainburns.domain.model.Card;
import com.brainburns.brainburns.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by arthan on 31.01.2017. | Project brainburns
 */

@RestController
@RequestMapping("/api/card")
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> createCard(@RequestBody Card card, Authentication auth) {
        getCardService().saveCard(auth.getName(), card);

        return createSuccessResponse();
    }

    private Map<String, Object> createSuccessResponse() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        return response;
    }

    public String test() {
        String var = cardService.tested("Var");
        System.out.println(var);
        return "Tested";
    }

    public CardService getCardService() {
        return cardService;
    }
}
