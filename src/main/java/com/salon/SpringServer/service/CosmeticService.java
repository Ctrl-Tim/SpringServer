package com.salon.SpringServer.service;

import com.salon.SpringServer.model.Cosmetic;
import com.salon.SpringServer.model.exception.CosmeticNotFoundException;
import com.salon.SpringServer.repository.CosmeticRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CosmeticService {

    private final CosmeticRepository cosmeticRepository;

    @Autowired
    public CosmeticService(CosmeticRepository cosmeticRepository) {
        this.cosmeticRepository = cosmeticRepository;
    }

    @Transactional
    public Cosmetic addCosmetic(Cosmetic cosmetic) {
        return cosmeticRepository.save(cosmetic);
    }

    public List<Cosmetic> getCosmetics() {
        return StreamSupport
                .stream(cosmeticRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Cosmetic getCosmetic(Long id) {
        return cosmeticRepository.findById(id).orElseThrow(() ->
                new CosmeticNotFoundException(id));
    }

    public Cosmetic deleteCosmetic(Long id) {
        Cosmetic cosmetic = getCosmetic(id);
        cosmeticRepository.delete(cosmetic);
        return cosmetic;
    }

    @Transactional
    public Cosmetic editCosmetic(Long id, Cosmetic cosmetic) {
        Cosmetic cosmeticToEdit = getCosmetic(id);
        cosmeticToEdit.setName(cosmetic.getName());
        cosmeticToEdit.setPrice(cosmetic.getPrice());
        return cosmeticToEdit;
    }
}
