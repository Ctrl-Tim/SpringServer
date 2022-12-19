package com.salon.SpringServer.repository;

import com.salon.SpringServer.model.Cosmetic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CosmeticRepository extends CrudRepository<Cosmetic, Long> {

}
