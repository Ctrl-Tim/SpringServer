package com.salon.SpringServer.model.dto;

import com.salon.SpringServer.model.Cosmetic;
import com.salon.SpringServer.model.Receipt;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ReceiptDto {
    private Long id;
    private String name;
    private LocalDateTime date;
    private List<CosmeticDto> cosmeticsDto = new ArrayList<>();

    public static ReceiptDto from(Receipt receipt) {
        ReceiptDto receiptDto = new ReceiptDto();
        receiptDto.setId(receipt.getId());
        receiptDto.setDate(receipt.getDate());
        receiptDto.setCosmeticsDto(receipt.getCosmetics().stream().map(CosmeticDto::from).collect(Collectors.toList()));
        return receiptDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<CosmeticDto> getCosmeticsDto() {
        return cosmeticsDto;
    }

    public void setCosmeticsDto(List<CosmeticDto> cosmeticsDto) {
        this.cosmeticsDto = cosmeticsDto;
    }
}
