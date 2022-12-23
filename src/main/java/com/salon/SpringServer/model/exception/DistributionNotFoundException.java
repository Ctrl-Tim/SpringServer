package com.salon.SpringServer.model.exception;

import java.text.MessageFormat;

public class DistributionNotFoundException extends RuntimeException{

    public DistributionNotFoundException(final Long id) {
        super(MessageFormat.format("Could not find distribution with id: {0}", id));
    }

}
