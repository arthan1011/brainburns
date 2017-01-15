package com.brainburns.brainburns.domain.mapper;

import com.brainburns.brainburns.domain.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * Created by arthan on 15.01.2017. | Project brainburns
 */

@Component
public interface UserMapper {

    @Select("SELECT username " +
            "FROM users " +
            "WHERE username=#{username}")
    User findByUsername(String username);


    @Insert("INSERT INTO users(username, password) " +
            "VALUES (#{username}, #{password})")
    @Options(keyColumn = "username", keyProperty = "username")
    void createUser(User user);
}
