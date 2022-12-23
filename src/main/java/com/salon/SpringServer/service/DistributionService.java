package com.salon.SpringServer.service;

import com.salon.SpringServer.model.Cosmetic;
import com.salon.SpringServer.model.Distribution;
import com.salon.SpringServer.model.DistributionDetail;
import com.salon.SpringServer.model.Visit;
import com.salon.SpringServer.model.exception.DistributionNotFoundException;
import com.salon.SpringServer.repository.DistributionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DistributionService {
    private final DistributionRepository distributionRepository;
    private final DistributionDetailService distributionDetailService;
    private final CosmeticService cosmeticService;
    private final VisitService visitService;

    public DistributionService(DistributionRepository distributionRepository, DistributionDetailService distributionDetailService,
                          CosmeticService cosmeticService, VisitService visitService) {
        this.distributionRepository = distributionRepository;
        this.distributionDetailService = distributionDetailService;
        this.cosmeticService = cosmeticService;
        this.visitService = visitService;
    }

    @Transactional
    public Distribution addDistribution(Distribution distribution) {
        return distributionRepository.save(distribution);
    }

    public List<Distribution> getDistributions() {
        return StreamSupport
                .stream(distributionRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Distribution getDistribution(Long id) {
        return distributionRepository.findById(id).orElseThrow(() ->
                new DistributionNotFoundException(id));
    }

    public Distribution deleteDistribution(Long id) {
        Distribution distribution = getDistribution(id);
        distributionRepository.delete(distribution);
        return distribution;
    }

    @Transactional
    public Distribution editDistribution(Long id, Distribution distribution) {
        Distribution distributionToEdit = getDistribution(id);
        distributionToEdit.setCosmetics(distribution.getCosmetics());
        distributionToEdit.setVisit(distribution.getVisit());
        return distributionToEdit;
    }

    @Transactional
    public Distribution addCosmeticToDistribution(Long distributionId, Long cosmeticId, int count) {
        Distribution distribution = getDistribution(distributionId);
        Cosmetic cosmetic = cosmeticService.getCosmetic(cosmeticId);

        for (DistributionDetail d : distribution.getCosmetics()) {
            if (d.getCosmetic().getId() == cosmeticId) {
                int prev_count = d.getCount();
                removeCosmeticFromDistribution(distributionId, cosmeticId);
                DistributionDetail edit_detail = new DistributionDetail();
                edit_detail.setDistribution(distribution);
                edit_detail.setCosmetic(cosmetic);
                edit_detail.setCount(prev_count + count);
                distribution.addCosmeticDistribution(edit_detail);
                return distribution;
            }
        }
        DistributionDetail detail = new DistributionDetail();
        detail.setDistribution(distribution);
        detail.setCosmetic(cosmetic);
        detail.setCount(count);
        distribution.addCosmeticDistribution(detail);
        return distribution;
    }

    @Transactional
    public Distribution removeCosmeticFromDistribution(Long distributionId, Long cosmeticId) {
        Distribution distribution = getDistribution(distributionId);
        DistributionDetail detail = new DistributionDetail();
        for (DistributionDetail d : distribution.getCosmetics()) {
            if (d.getCosmetic().getId() == cosmeticId) {
                detail = d;
            }
        }
        distribution.removeCosmeticDistribution(detail);
        distributionDetailService.deleteDistributionDetail(detail.getId());
        return distribution;
    }

    @Transactional
    public Distribution distributionBindingVisit(Long distributionId, Long visitId) {
        Distribution distribution = getDistribution(distributionId);
        Visit visit = visitService.getVisit(visitId);
        distribution.distributionBindingVisit(distribution, visit);
        return distribution;
    }

    @Transactional
    public Distribution distributionDecouplingVisit(Long distributionId) {
        Distribution distribution = getDistribution(distributionId);
        distribution.distributionDecouplingVisit();
        return distribution;
    }
}
