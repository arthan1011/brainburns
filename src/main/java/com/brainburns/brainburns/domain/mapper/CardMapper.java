package com.brainburns.brainburns.domain.mapper;

import com.brainburns.brainburns.domain.model.Card;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * Created by arthan on 01.02.2017. | Project brainburns
 */

@Component
public interface CardMapper {

    // Using @Select because of RETURNING clause
    @Select("INSERT INTO card (deskid, writing, transcription, meaning) " +
            "VALUES (#{deskId}, #{writing}, #{transcription}, #{meaning}) " +
            "RETURNING cardid")
    int createCard(Card card);
}
