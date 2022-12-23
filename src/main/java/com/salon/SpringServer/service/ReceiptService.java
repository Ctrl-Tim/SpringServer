package com.salon.SpringServer.service;

import com.salon.SpringServer.model.Cosmetic;
import com.salon.SpringServer.model.Receipt;
import com.salon.SpringServer.model.ReceiptDetail;
import com.salon.SpringServer.model.exception.ReceiptNotFoundException;
import com.salon.SpringServer.repository.ReceiptRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final ReceiptDetailService receiptDetailService;
    private final CosmeticService cosmeticService;

    public ReceiptService(ReceiptRepository receiptRepository, ReceiptDetailService receiptDetailService,
                          CosmeticService cosmeticService) {
        this.receiptRepository = receiptRepository;
        this.receiptDetailService = receiptDetailService;
        this.cosmeticService = cosmeticService;
    }

    @Transactional
    public Receipt addReceipt(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    public List<Receipt> getReceipts() {
        return StreamSupport
                .stream(receiptRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Receipt getReceipt(Long id) {
        return receiptRepository.findById(id).orElseThrow(() ->
                new ReceiptNotFoundException(id));
    }

    public Receipt deleteReceipt(Long id) {
        Receipt receipt = getReceipt(id);
        receiptRepository.delete(receipt);
        return receipt;
    }

    @Transactional
    public Receipt editReceipt(Long id, Receipt receipt) {
        Receipt receiptToEdit = getReceipt(id);
        receiptToEdit.setDate(receipt.getDate());
        receiptToEdit.setTotal(receipt.getTotal());
        receiptToEdit.setCosmetics(receipt.getCosmetics());
        return receiptToEdit;
    }

    @Transactional
    public Receipt addCosmeticToReceipt(Long receiptId, Long cosmeticId, int count) {
        Receipt receipt = getReceipt(receiptId);
        Cosmetic cosmetic = cosmeticService.getCosmetic(cosmeticId);

        for (ReceiptDetail d : receipt.getCosmetics()) {
            if (d.getCosmetic().getId() == cosmeticId) {
                int prev_count = d.getCount();
                removeCosmeticFromReceipt(receiptId, cosmeticId);
                ReceiptDetail edit_detail = new ReceiptDetail();
                edit_detail.setReceipt(receipt);
                edit_detail.setCosmetic(cosmetic);
                edit_detail.setCount(prev_count + count);
                edit_detail.setSubtotal(cosmetic.getPrice() * (prev_count + count));
                receipt.addCosmeticReceipt(edit_detail);
                receipt.SumTotal();
                return receipt;
            }
        }
        ReceiptDetail detail = new ReceiptDetail();
        detail.setReceipt(receipt);
        detail.setCosmetic(cosmetic);
        detail.setCount(count);
        detail.setSubtotal(cosmetic.getPrice() * count);
        receipt.addCosmeticReceipt(detail);
        receipt.SumTotal();
        return receipt;
    }

    @Transactional
    public Receipt removeCosmeticFromReceipt(Long receiptId, Long cosmeticId) {
        Receipt receipt = getReceipt(receiptId);
        ReceiptDetail detail = new ReceiptDetail();
        for (ReceiptDetail d : receipt.getCosmetics()) {
            if (d.getCosmetic().getId() == cosmeticId) {
                detail = d;
            }
        }
        receipt.removeCosmeticReceipt(detail);
        receiptDetailService.deleteReceiptDetail(detail.getId());
        receipt.SumTotal();
        return receipt;
    }

    @Transactional
    public ReceiptDetail removeCosmeticFromReceipt(Long detailId) {
        ReceiptDetail receiptDetail = receiptDetailService.getReceiptDetail(detailId);
        receiptDetailService.deleteReceiptDetail(receiptDetail.getId());
        return receiptDetail;
    }
}