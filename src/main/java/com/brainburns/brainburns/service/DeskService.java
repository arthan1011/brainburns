package com.brainburns.brainburns.service;

import com.brainburns.brainburns.domain.mapper.DeskMapper;
import com.brainburns.brainburns.domain.model.Desk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by arthan on 20.01.2017. | Project brainburns
 */

@Service
@Transactional
public class DeskService {

    private final DeskMapper deskMapper;

    @Autowired
    public DeskService(DeskMapper deskMapper) {
        this.deskMapper = deskMapper;
    }

    public Desk findById(long id) {
        return deskMapper.findById(id);
    }

    public List<Desk> findByUsername(String username) {
        return deskMapper.findByUsername(username);
    }

    public long createDesk(Desk desk) {
        List<Desk> userDesks = deskMapper.findByUsername(desk.getUsername());

        boolean userHasDeskWithTheSameName = userDesks.stream()
                .anyMatch(item -> item.getTitle().equals(desk.getTitle()));
        if (userHasDeskWithTheSameName) {
            // TODO: Raise exception here, no return
            return -1;
        } else {
            return deskMapper.createDesk(desk);
        }
    }
}
