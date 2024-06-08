package org.kangnam.containlaw.service;

import org.kangnam.containlaw.entity.Bill;
import org.kangnam.containlaw.entity.Category;
import org.kangnam.containlaw.repository.BillRepositoryImpl;
import org.kangnam.containlaw.repository.CategoryRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private BillRepositoryImpl billRepository;

    @Autowired
    private CategoryRepositoryImpl categoryRepository;

    public List<Bill> findBillsByCategory(String categoryName) {
        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        return category.getBills();
    }

}
