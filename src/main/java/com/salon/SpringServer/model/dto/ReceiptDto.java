package com.salon.SpringServer.model.dto;

import com.salon.SpringServer.model.Receipt;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ReceiptDto {
    private Long id;
    private LocalDateTime date;
    private float total;
    private List<ReceiptDetailDto> cosmetics = new ArrayList<>();

    public static ReceiptDto from(Receipt receipt) {
        ReceiptDto receiptDto = new ReceiptDto();
        receiptDto.setId(receipt.getId());
        receiptDto.setDate(receipt.getDate());
        receiptDto.setTotal(receipt.getTotal());
        receiptDto.setCosmetics(receipt.getCosmetics().stream().map(ReceiptDetailDto::from).collect(Collectors.toList()));
        return receiptDto;
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

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public List<ReceiptDetailDto> getCosmetics() {
        return cosmetics;
    }

    public void setCosmetics(List<ReceiptDetailDto> cosmetics) {
        this.cosmetics = cosmetics;
    }

    public void addCosmetic(ReceiptDetailDto receiptDetailDto) { cosmetics.add(receiptDetailDto); }
}
