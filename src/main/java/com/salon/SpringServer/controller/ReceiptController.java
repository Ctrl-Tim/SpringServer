package com.salon.SpringServer.controller;

import com.salon.SpringServer.model.Receipt;
import com.salon.SpringServer.model.ReceiptDetail;
import com.salon.SpringServer.model.dto.ReceiptDetailDto;
import com.salon.SpringServer.model.dto.ReceiptDto;
import com.salon.SpringServer.service.ReceiptDetailService;
import com.salon.SpringServer.service.ReceiptService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;
    private final ReceiptDetailService receiptDetailService;

    @Autowired
    public ReceiptController(ReceiptService receiptService, ReceiptDetailService receiptDetailService) {
        this.receiptService = receiptService;
        this.receiptDetailService = receiptDetailService;
    }

    @PostMapping
    public ResponseEntity<ReceiptDto> addReceipt(@RequestBody final ReceiptDto receiptDto) {
        Receipt receipt = receiptService.addReceipt(Receipt.from(receiptDto));
        return new ResponseEntity<>(ReceiptDto.from(receipt), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ReceiptDto>> getReceipts() {
        List<Receipt> receipts = receiptService.getReceipts();
        List<ReceiptDto> receiptsDto = receipts.stream().map(ReceiptDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(receiptsDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<ReceiptDto> getReceipt(@PathVariable final Long id) {
        Receipt receipt = receiptService.getReceipt(id);
        return new ResponseEntity<>(ReceiptDto.from(receipt), HttpStatus.OK);
    }

    @GetMapping(value = "{id}/cosmetics")
    public ResponseEntity<List<ReceiptDetailDto>> getReceiptDetails(@PathVariable final Long id) {
        List<ReceiptDetail> details = receiptService.getReceipt(id).getCosmetics();
        List<ReceiptDetailDto> detailsDto = details.stream().map(ReceiptDetailDto::from).collect(Collectors.toList());
        return new ResponseEntity(detailsDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<ReceiptDto> deleteReceipt(@PathVariable final Long id) {
        Receipt receipt = receiptService.deleteReceipt(id);
        return new ResponseEntity<>(ReceiptDto.from(receipt), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<ReceiptDto> editReceipt(@PathVariable final Long id,
                                                  @RequestBody final ReceiptDto receiptDto) {
        Receipt receipt = receiptService.editReceipt(id, Receipt.from(receiptDto));
        return new ResponseEntity<>(ReceiptDto.from(receipt), HttpStatus.OK);
    }

    @PostMapping(value = "{receiptId}/cosmetics/add/{cosmeticId}/{count}")
    public ResponseEntity<ReceiptDto> addCosmeticToReceipt(@PathVariable final Long receiptId,
                                                           @PathVariable final Long cosmeticId,
                                                           @PathVariable final int count) {
        Receipt receipt = receiptService.addCosmeticToReceipt(receiptId, cosmeticId, count);
        return new ResponseEntity<>(ReceiptDto.from(receipt), HttpStatus.OK);
    }

    @DeleteMapping(value = "{receiptId}/cosmetics/remove/{cosmeticId}")
    public ResponseEntity<ReceiptDto> removeCosmeticFromReceipt(@PathVariable final Long receiptId,
                                                           @PathVariable final Long cosmeticId) {
        Receipt receipt = receiptService.removeCosmeticFromReceipt(receiptId, cosmeticId);
        return new ResponseEntity<>(ReceiptDto.from(receipt), HttpStatus.OK);
    }
}