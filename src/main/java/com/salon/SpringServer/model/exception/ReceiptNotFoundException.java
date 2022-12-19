package com.salon.SpringServer.model.exception;

import java.text.MessageFormat;

public class ReceiptNotFoundException extends RuntimeException {

    public ReceiptNotFoundException(final Long id) {
        super(MessageFormat.format("Could not find receipt with id: {0}", id));
    }
}
