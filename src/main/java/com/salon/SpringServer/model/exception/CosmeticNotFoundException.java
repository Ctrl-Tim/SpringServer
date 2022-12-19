package com.salon.SpringServer.model.exception;

import java.text.MessageFormat;

public class CosmeticNotFoundException extends RuntimeException{

    public CosmeticNotFoundException(final Long id) {
        super(MessageFormat.format("Could not find cosmetic with id: {0}", id));
    }
}
