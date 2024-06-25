package com.example.KiemTra2.service;

import com.example.KiemTra2.entity.Category;
import com.example.KiemTra2.entity.Product;
import com.example.KiemTra2.repository.CategoryRepository;
import com.example.KiemTra2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public List<Category> GetAll() {return (List<Category>) categoryRepository.findAll(); }

    public void add(Category newCategory){ categoryRepository.save(newCategory); }
}
