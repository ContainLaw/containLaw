package org.kangnam.containlaw.repository;

import org.kangnam.containlaw.entity.BillMemberProfile;
import org.kangnam.containlaw.entity.BillMemberProfileId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface iBillMemberProfileRepository extends JpaRepository<BillMemberProfile, BillMemberProfileId>{
}
