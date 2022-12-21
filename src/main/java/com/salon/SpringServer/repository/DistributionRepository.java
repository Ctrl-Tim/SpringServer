package com.salon.SpringServer.repository;

import com.salon.SpringServer.model.Distribution;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistributionRepository extends CrudRepository<Distribution, Long>{

}
