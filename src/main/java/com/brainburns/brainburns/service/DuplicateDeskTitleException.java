package com.brainburns.brainburns.service;

/**
 * Created by arthan on 28.02.2017. | Project brainburns
 */

public class DuplicateDeskTitleException extends RuntimeException {

    public DuplicateDeskTitleException(String message) {
        super(message);
    }
}
