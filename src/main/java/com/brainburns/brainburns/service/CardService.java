package com.brainburns.brainburns.service;

import com.brainburns.brainburns.domain.mapper.CardMapper;
import com.brainburns.brainburns.domain.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by arthan on 31.01.2017. | Project brainburns
 */

@Service
@Transactional
public class CardService {

    @Autowired
    private CardMapper cardMapper;

    public Card saveCard(String username, Card newCard) {
        long cardId = cardMapper.createCard(newCard);
        return cardMapper.findById(cardId);
    }


    public List<Card> getCardsInDesk(long deskId) {
        return cardMapper.findByDesk(deskId);
    }
}
