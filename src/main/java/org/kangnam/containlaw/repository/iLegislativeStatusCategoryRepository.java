package org.kangnam.containlaw.repository;


import org.kangnam.containlaw.entity.LegislativeStatusCategory;
import org.kangnam.containlaw.entity.LegislativeStatusCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface iLegislativeStatusCategoryRepository extends JpaRepository<LegislativeStatusCategory, LegislativeStatusCategoryId> {
}
