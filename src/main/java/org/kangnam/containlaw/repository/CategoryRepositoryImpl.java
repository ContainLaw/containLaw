package org.kangnam.containlaw.repository;

import org.kangnam.containlaw.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepositoryImpl extends JpaRepository<Category, Integer> {
    Optional<Category> findById (Integer id);

}
