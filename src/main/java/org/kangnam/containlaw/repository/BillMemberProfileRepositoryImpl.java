package org.kangnam.containlaw.repository;

import org.kangnam.containlaw.entity.Bill;
import org.kangnam.containlaw.entity.BillMemberProfile;
import org.kangnam.containlaw.entity.MemberProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillMemberProfileRepositoryImpl extends JpaRepository<BillMemberProfile, Long> {
    boolean existsByBillAndMemberProfile(Bill bill, MemberProfile memberProfile);
}
