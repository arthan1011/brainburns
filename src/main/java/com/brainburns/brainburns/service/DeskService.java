package com.brainburns.brainburns.service;

import com.brainburns.brainburns.domain.mapper.DeskMapper;
import com.brainburns.brainburns.domain.model.Desk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
