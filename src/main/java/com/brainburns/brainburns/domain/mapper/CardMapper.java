package com.brainburns.brainburns.domain.mapper;

import com.brainburns.brainburns.domain.model.Card;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
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
    long createCard(Card card);

    @Select("SELECT cardid, deskid, writing, transcription, meaning " +
            "FROM card " +
            "WHERE cardid=#{cardId}")
    @Results({
            @Result(property = "id", column = "cardid")
    })
    Card findById(long cardId);
}
