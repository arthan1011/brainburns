package com.brainburns.brainburns.service;

import com.brainburns.brainburns.domain.mapper.AuthorityMapper;
import com.brainburns.brainburns.domain.mapper.UserMapper;
import com.brainburns.brainburns.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by Arthan on 23.12.2016. Project: brainburns
 */

@Service
@Transactional("transactionManager")
public class UserService {

    private final UserMapper userMapper;
    private final AuthorityMapper authorityMapper;

    @Autowired
    public UserService(UserMapper userMapper,
                       AuthorityMapper authorityMapper) {
        this.userMapper = userMapper;
        this.authorityMapper = authorityMapper;
    }

    public Optional<User> findByUsername(String username) {
        User user = userMapper.findByUsername(username);
        return Optional.ofNullable(user);
    }

    public void createUser(User user) {
        userMapper.createUser(user);
        authorityMapper.createUserAuthority(user);
    }
}
