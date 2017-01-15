package com.brainburns.brainburns.domain.mapper;

import com.brainburns.brainburns.domain.model.User;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;

/**
 * Created by arthan on 15.01.2017. | Project brainburns
 */

@Component
public interface AuthorityMapper {

    @Insert("INSERT INTO authorities(username, authority) " +
            "VALUES (#{username}, 'USER')")
    void createUserAuthority(User user);
}
