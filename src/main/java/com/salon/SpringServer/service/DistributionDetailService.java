package com.salon.SpringServer.service;

import com.salon.SpringServer.model.DistributionDetail;
import com.salon.SpringServer.model.exception.CosmeticNotFoundException;
import com.salon.SpringServer.repository.DistributionDetailRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DistributionDetailService {
    private final DistributionDetailRepository distributionDetailRepository;
    private final CosmeticService cosmeticService;

    @Autowired
    public DistributionDetailService(DistributionDetailRepository distributionDetailRepository, CosmeticService cosmeticService) {
        this.distributionDetailRepository = distributionDetailRepository;
        this.cosmeticService = cosmeticService;
    }

    @Transactional
    public DistributionDetail addDistributionDetail(DistributionDetail distributionDetail) {
        return distributionDetailRepository.save(distributionDetail);
    }

    public List<DistributionDetail> getDistributionDetails() {
        return StreamSupport
                .stream(distributionDetailRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public DistributionDetail getDistributionDetail(Long id) {
        return distributionDetailRepository.findById(id).orElseThrow(() ->
                new CosmeticNotFoundException(id));
    }

    @Transactional
    public DistributionDetail deleteDistributionDetail(Long id) {
        DistributionDetail distributionDetail = getDistributionDetail(id);
        distributionDetailRepository.delete(distributionDetail);
        return distributionDetail;
    }

    @Transactional
    public DistributionDetail editDistributionDetail(Long id, DistributionDetail distributionDetail) {
        DistributionDetail distributionDetailToEdit = getDistributionDetail(id);
        distributionDetailToEdit.setCosmetic(distributionDetail.getCosmetic());
        distributionDetailToEdit.setDistribution(distributionDetail.getDistribution());
        distributionDetailToEdit.setCount(distributionDetail.getCount());
        return distributionDetailToEdit;
    }
}
