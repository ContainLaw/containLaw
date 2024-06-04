package org.kangnam.containlaw.repository;

import org.kangnam.containlaw.entity.MemberProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberProfileRepositoryImpl extends JpaRepository<MemberProfile, Long> {
    Optional<MemberProfile> findByName(String name);
    List<MemberProfile> findByNameContaining(String name);
    List<MemberProfile> findByPartyNameContaining (String partyName);
    List<MemberProfile> findByDistrictContaining (String district);
}
