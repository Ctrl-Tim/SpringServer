package com.salon.SpringServer.repository;

import com.salon.SpringServer.model.ReceiptDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptDetailRepository extends CrudRepository<ReceiptDetail, Long> {

}
