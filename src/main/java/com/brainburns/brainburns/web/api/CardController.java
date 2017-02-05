package com.brainburns.brainburns.web.api;

import com.brainburns.brainburns.domain.model.Card;
import com.brainburns.brainburns.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        Card savedCard = cardService.saveCard(auth.getName(), card);

        Map<String, Object> response = createSuccessResponse();
        List<Card> cards = new ArrayList<>();
        cards.add(savedCard);
        response.put("data", cards);
        return response;
    }

    @GetMapping()
    public List<Card> getCards(@RequestParam long deskId) {
        // todo: User should have access only to his own cards
        return cardService.getCardsInDesk(deskId);
    }

    private Map<String, Object> createSuccessResponse() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        return response;
    }
}
