package com.salon.SpringServer.model.exception;

import java.text.MessageFormat;

public class CosmeticIsAlreadyAssignedException extends RuntimeException {
    public CosmeticIsAlreadyAssignedException(final Long cosmeticId, final Long receiptId) {
        super(MessageFormat.format("Cosmetic: {0} is already assigned to receipt: {1}", cosmeticId, receiptId));
    }
}
