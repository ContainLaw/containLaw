package org.kangnam.containlaw.repository;

import org.kangnam.containlaw.entity.MemberProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberProfileRepository extends JpaRepository<MemberProfile, Long> {
    List<MemberProfile> findByNameContaining(String name);
    List<MemberProfile> findByPartyNameContaining (String partyName);
    List<MemberProfile> findByDistrictContaining (String district);
}
