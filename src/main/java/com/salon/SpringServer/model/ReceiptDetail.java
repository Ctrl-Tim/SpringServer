package com.salon.SpringServer.model;

import com.salon.SpringServer.model.dto.ReceiptDetailDto;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "receipt_details")
public class ReceiptDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cosmetic_id")
    private Cosmetic cosmetic;

    @ManyToOne
    @JoinColumn(name = "receipt_id")
    private Receipt receipt;

    private int count;
    private float subtotal;

    public ReceiptDetail() { }

    public static ReceiptDetail from(ReceiptDetailDto rdd) {
        ReceiptDetail rd = new ReceiptDetail();
        rd.setCount(rdd.getCount());
        rd.setSubtotal(rdd.getSubtotal());
        return rd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cosmetic getCosmetic() {
        return cosmetic;
    }

    public void setCosmetic(Cosmetic cosmetic) {
        this.cosmetic = cosmetic;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }
}
