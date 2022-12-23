package com.salon.SpringServer.model.exception;

import java.text.MessageFormat;

public class ClientNotFoundException extends RuntimeException{

    public ClientNotFoundException(Long id) {
        super(MessageFormat.format("Could not find client with id: {0}", id));
    }
}
