package com.salon.SpringServer.repository;

import com.salon.SpringServer.model.DistributionDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistributionDetailRepository extends CrudRepository<DistributionDetail, Long> {

}
