package com.salon.SpringServer.controller;

import com.salon.SpringServer.model.Receipt;
import com.salon.SpringServer.model.dto.ReceiptDto;
import com.salon.SpringServer.service.ReceiptService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;

    @Autowired
    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
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

    @PostMapping(value = "{receiptId}/cosmetics/{cosmeticId}/add")
    public ResponseEntity<ReceiptDto> addCosmeticToReceipt(@PathVariable final Long receiptId,
                                                           @PathVariable final Long cosmeticId) {
        Receipt receipt = receiptService.addCosmeticToReceipt(receiptId, cosmeticId);
        return new ResponseEntity<>(ReceiptDto.from(receipt), HttpStatus.OK);
    }

    @DeleteMapping(value = "{receiptId}/cosmetics/{cosmeticId}/remove")
    public ResponseEntity<ReceiptDto> removeCosmeticFromReceipt(@PathVariable final Long receiptId,
                                                           @PathVariable final Long cosmeticId) {
        Receipt receipt = receiptService.removeCosmeticFromReceipt(receiptId, cosmeticId);
        return new ResponseEntity<>(ReceiptDto.from(receipt), HttpStatus.OK);
    }
}