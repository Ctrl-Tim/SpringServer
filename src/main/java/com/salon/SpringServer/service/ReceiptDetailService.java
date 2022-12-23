package com.salon.SpringServer.service;

import com.salon.SpringServer.model.ReceiptDetail;
import com.salon.SpringServer.model.exception.CosmeticNotFoundException;
import com.salon.SpringServer.repository.ReceiptDetailRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ReceiptDetailService {
    private final ReceiptDetailRepository receiptDetailRepository;
    private final CosmeticService cosmeticService;

    @Autowired
    public ReceiptDetailService(ReceiptDetailRepository receiptDetailRepository, CosmeticService cosmeticService) {
        this.receiptDetailRepository = receiptDetailRepository;
        this.cosmeticService = cosmeticService;
    }

    @Transactional
    public ReceiptDetail addReceiptDetail(ReceiptDetail receiptDetail) {
        return receiptDetailRepository.save(receiptDetail);
    }

    public List<ReceiptDetail> getReceiptDetails() {
        return StreamSupport
                .stream(receiptDetailRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public ReceiptDetail getReceiptDetail(Long id) {
        return receiptDetailRepository.findById(id).orElseThrow(() ->
                new CosmeticNotFoundException(id));
    }

    @Transactional
    public ReceiptDetail deleteReceiptDetail(Long id) {
        ReceiptDetail receiptDetail = getReceiptDetail(id);
        receiptDetailRepository.delete(receiptDetail);
        return receiptDetail;
    }

    @Transactional
    public ReceiptDetail editReceiptDetail(Long id, ReceiptDetail receiptDetail) {
        ReceiptDetail receiptDetailToEdit = getReceiptDetail(id);
        receiptDetailToEdit.setCosmetic(receiptDetail.getCosmetic());
        receiptDetailToEdit.setReceipt(receiptDetail.getReceipt());
        receiptDetailToEdit.setCount(receiptDetail.getCount());
        receiptDetailToEdit.setSubtotal(receiptDetail.getCount());
        return receiptDetailToEdit;
    }
}
