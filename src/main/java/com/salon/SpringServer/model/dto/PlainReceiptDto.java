package com.salon.SpringServer.model.dto;

import com.salon.SpringServer.model.Receipt;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PlainReceiptDto {
    private Long id;
    private LocalDateTime date;

    public static PlainReceiptDto from(Receipt receipt) {
        PlainReceiptDto plainReceiptDto = new PlainReceiptDto();
        plainReceiptDto.setId(receipt.getId());
        plainReceiptDto.setDate(receipt.getDate());
        return plainReceiptDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
