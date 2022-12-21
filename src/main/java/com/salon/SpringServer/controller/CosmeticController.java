package com.salon.SpringServer.controller;

import com.salon.SpringServer.model.Cosmetic;
import com.salon.SpringServer.model.dto.CosmeticDto;
import com.salon.SpringServer.service.CosmeticService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cosmetics")
public class CosmeticController {

    private final CosmeticService cosmeticService;

    @Autowired
    public CosmeticController(CosmeticService cosmeticService) {
        this.cosmeticService = cosmeticService;
    }

    @PostMapping
    public ResponseEntity<CosmeticDto> addCosmetic(@RequestBody final CosmeticDto cosmeticDto) {
        Cosmetic cosmetic = cosmeticService.addCosmetic(Cosmetic.from(cosmeticDto));
        return new ResponseEntity<>(CosmeticDto.from(cosmetic), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CosmeticDto>> getCosmetics() {
        List<Cosmetic> cosmetics = cosmeticService.getCosmetics();
        List<CosmeticDto> cosmeticsDto = cosmetics.stream().map(CosmeticDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(cosmeticsDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<CosmeticDto> getCosmetic(@PathVariable final Long id) {
        Cosmetic cosmetic = cosmeticService.getCosmetic(id);
        return new ResponseEntity<>(CosmeticDto.from(cosmetic), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<CosmeticDto> deleteCosmetic(@PathVariable final Long id) {
        Cosmetic cosmetic = cosmeticService.deleteCosmetic(id);
        return new ResponseEntity<>(CosmeticDto.from(cosmetic), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<CosmeticDto> editCosmetic(@PathVariable final Long id,
                                                    @RequestBody final CosmeticDto cosmeticDto) {
        Cosmetic editedCosmetic = cosmeticService.editCosmetic(id, Cosmetic.from(cosmeticDto));
        return new ResponseEntity<>(CosmeticDto.from(editedCosmetic), HttpStatus.OK);
    }
}
