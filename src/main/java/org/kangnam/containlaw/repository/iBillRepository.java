package org.kangnam.containlaw.repository;

import org.kangnam.containlaw.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface iBillRepository extends JpaRepository<Bill, String> {
    List<Bill> findByBillNameContaining(String billName);
}

