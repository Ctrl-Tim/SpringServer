package com.salon.SpringServer.model;

import com.salon.SpringServer.model.dto.ReceiptDetailDto;
import com.salon.SpringServer.model.dto.ReceiptDto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "receipts")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private float total;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "receipt", cascade = CascadeType.ALL)
    private List<ReceiptDetail> cosmetics = new ArrayList<>();

    public Receipt () { }

    public static Receipt from(ReceiptDto receiptDto) {
        Receipt receipt = new Receipt();
        receipt.setDate(receiptDto.getDate());
        receipt.setTotal(receiptDto.getTotal());
        receipt.setCosmetics(receiptDto.getCosmetics().stream().map(ReceiptDetail::from).collect(Collectors.toList()));
        return receipt;
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

    public List<ReceiptDetail> getCosmetics() { return cosmetics; }

    public void setCosmetics(List<ReceiptDetail> cosmetics) {
        this.cosmetics = cosmetics;
    }

    public void addCosmeticReceipt(ReceiptDetail receiptDetail) {
        cosmetics.add(receiptDetail);
    }

    public void removeCosmeticReceipt(ReceiptDetail receiptDetail) { cosmetics.remove(receiptDetail); }
}
