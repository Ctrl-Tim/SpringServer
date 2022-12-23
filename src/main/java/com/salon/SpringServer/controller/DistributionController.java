package com.salon.SpringServer.controller;

import com.salon.SpringServer.model.Distribution;
import com.salon.SpringServer.model.DistributionDetail;
import com.salon.SpringServer.model.dto.DistributionDto;
import com.salon.SpringServer.model.dto.DistributionDetailDto;
import com.salon.SpringServer.service.DistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/distributions")
public class DistributionController {

    private final DistributionService distributionService;

    @Autowired
    public DistributionController(DistributionService distributionService) {
        this.distributionService = distributionService;
    }

    @PostMapping
    public ResponseEntity<DistributionDto> addDistribution(@RequestBody final DistributionDto distributionDto) {
        Distribution distribution = distributionService.addDistribution(Distribution.from(distributionDto));
        return new ResponseEntity<>(DistributionDto.from(distribution), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DistributionDto>> getDistributions() {
        List<Distribution> distributions = distributionService.getDistributions();
        List<DistributionDto> distributionsDto = distributions.stream().map(DistributionDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(distributionsDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<DistributionDto> getDistribution(@PathVariable final Long id) {
        Distribution distribution = distributionService.getDistribution(id);
        return new ResponseEntity<>(DistributionDto.from(distribution), HttpStatus.OK);
    }

    @GetMapping(value = "{id}/cosmetics")
    public ResponseEntity<List<DistributionDetailDto>> getDistributionDetails(@PathVariable final Long id) {
        List<DistributionDetail> details = distributionService.getDistribution(id).getCosmetics();
        List<DistributionDetailDto> detailsDto = details.stream().map(DistributionDetailDto::from).collect(Collectors.toList());
        return new ResponseEntity(detailsDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<DistributionDto> deleteDistribution(@PathVariable final Long id) {
        Distribution distribution = distributionService.deleteDistribution(id);
        return new ResponseEntity<>(DistributionDto.from(distribution), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<DistributionDto> editDistribution(@PathVariable final Long id,
                                                  @RequestBody final DistributionDto distributionDto) {
        Distribution distribution = distributionService.editDistribution(id, Distribution.from(distributionDto));
        return new ResponseEntity<>(DistributionDto.from(distribution), HttpStatus.OK);
    }

    @PostMapping(value = "{distributionId}/cosmetics/add/{cosmeticId}/{count}")
    public ResponseEntity<DistributionDto> addCosmeticToDistribution(@PathVariable final Long distributionId,
                                                           @PathVariable final Long cosmeticId,
                                                           @PathVariable final int count) {
        Distribution distribution = distributionService.addCosmeticToDistribution(distributionId, cosmeticId, count);
        return new ResponseEntity<>(DistributionDto.from(distribution), HttpStatus.OK);
    }

    @DeleteMapping(value = "{distributionId}/cosmetics/remove/{cosmeticId}")
    public ResponseEntity<DistributionDto> removeCosmeticFromDistribution(@PathVariable final Long distributionId,
                                                                @PathVariable final Long cosmeticId) {
        Distribution distribution = distributionService.removeCosmeticFromDistribution(distributionId, cosmeticId);
        return new ResponseEntity<>(DistributionDto.from(distribution), HttpStatus.OK);
    }

    @PostMapping(value = "{distributionId}/visit/add/{visitId}")
    public ResponseEntity<DistributionDto> DistributionBindingVisit(@PathVariable final Long distributionId,
                                                                     @PathVariable final Long visitId) {
        Distribution distribution = distributionService.distributionBindingVisit(distributionId, visitId);
        return new ResponseEntity<>(DistributionDto.from(distribution), HttpStatus.OK);
    }

    @DeleteMapping(value = "{distributionId}/visit/clear")
    public ResponseEntity<DistributionDto> DistributionDecouplingVisit(@PathVariable final Long distributionId) {
        Distribution distribution = distributionService.distributionDecouplingVisit(distributionId);
        return new ResponseEntity<>(DistributionDto.from(distribution), HttpStatus.OK);
    }
}
