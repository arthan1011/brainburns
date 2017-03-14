package com.brainburns.brainburns.service;

import com.brainburns.brainburns.domain.mapper.AuthorityMapper;
import com.brainburns.brainburns.domain.mapper.DeskMapper;
import com.brainburns.brainburns.domain.mapper.UserMapper;
import com.brainburns.brainburns.domain.model.Desk;
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

    public static final String DEFAULT_DESK_NAME = "Default";

    private final UserMapper userMapper;
    private final AuthorityMapper authorityMapper;
    private final DeskMapper deskMapper;

    @Autowired
    public UserService(
            UserMapper userMapper,
            AuthorityMapper authorityMapper,
            DeskMapper deskMapper
    ) {
        this.userMapper = userMapper;
        this.authorityMapper = authorityMapper;
        this.deskMapper = deskMapper;
    }

    public Optional<User> findByUsername(String username) {
        User user = userMapper.findByUsername(username);
        return Optional.ofNullable(user);
    }

    public void createUser(User user) {
        userMapper.createUser(user);
        Desk defaultDesk = constructDefaultDesk(user.getUsername());
        deskMapper.createDesk(defaultDesk);
        authorityMapper.createUserAuthority(user);
    }

    private Desk constructDefaultDesk(String username) {
        Desk desk = new Desk();
        desk.setUsername(username);
        desk.setTitle(DEFAULT_DESK_NAME);
        return desk;
    }
}
