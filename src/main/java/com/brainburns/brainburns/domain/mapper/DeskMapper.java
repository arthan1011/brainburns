package com.brainburns.brainburns.domain.mapper;

import com.brainburns.brainburns.domain.model.Desk;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by arthan on 20.01.2017. | Project brainburns
 */

@Component
public interface DeskMapper {

    @Select("SELECT deskid, username, title " +
            "FROM desk " +
            "WHERE deskid=#{id}")
    @Results({
            @Result(property = "id", column = "deskid")
    })
    Desk findById(long id);

    @Select("SELECT deskid, username, title " +
            "FROM desk " +
            "WHERE username=#{username}")
    @Results({
            @Result(property = "id", column = "deskid")
    })
    List<Desk> findByUsername(String username);
}
