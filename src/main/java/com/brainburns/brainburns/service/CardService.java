package com.brainburns.brainburns.service;

import com.brainburns.brainburns.domain.mapper.CardMapper;
import com.brainburns.brainburns.domain.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by arthan on 31.01.2017. | Project brainburns
 */

@Service
@Transactional
public class CardService {

    @Autowired
    private CardMapper cardMapper;

    public void saveCard(String username, Card newCard) {
        cardMapper.createCard(newCard);
    }

}
