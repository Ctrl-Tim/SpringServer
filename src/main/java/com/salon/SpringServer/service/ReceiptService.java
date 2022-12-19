package com.salon.SpringServer.service;

import com.salon.SpringServer.model.Cosmetic;
import com.salon.SpringServer.model.Receipt;
import com.salon.SpringServer.model.exception.CosmeticIsAlreadyAssignedException;
import com.salon.SpringServer.model.exception.ReceiptNotFoundException;
import com.salon.SpringServer.repository.CosmeticRepository;
import com.salon.SpringServer.repository.ReceiptRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final CosmeticService cosmeticService;

    public ReceiptService(ReceiptRepository receiptRepository, CosmeticService cosmeticService) {
        this.receiptRepository = receiptRepository;
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
        return receiptToEdit;
    }

    @Transactional
    public Receipt addCosmeticToReceipt(Long receiptId, Long cosmeticId) {
        Receipt receipt = getReceipt(receiptId);
        Cosmetic cosmetic = cosmeticService.getCosmetic(cosmeticId);
        if (Objects.nonNull(cosmetic.getReceipt())) {
            throw new CosmeticIsAlreadyAssignedException(cosmeticId, cosmetic.getReceipt().getId());
        }
        receipt.addCosmetic(cosmetic);
        return receipt;
    }

    @Transactional
    public Receipt removeCosmeticFromReceipt(Long receiptId, Long cosmeticId) {
        Receipt receipt = getReceipt(receiptId);
        Cosmetic cosmetic = cosmeticService.getCosmetic(cosmeticId);
        receipt.removeCosmetic(cosmetic);
        return receipt;
    }
}
