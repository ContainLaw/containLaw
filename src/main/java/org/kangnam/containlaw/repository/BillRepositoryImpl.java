package org.kangnam.containlaw.repository;


import org.kangnam.containlaw.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepositoryImpl extends JpaRepository<Bill, String> {
    List<Bill> findByBillNameContaining(String billName);
}

