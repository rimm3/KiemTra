package com.example.KiemTra2.service;

import com.example.KiemTra2.entity.Product;
import com.example.KiemTra2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }
    public List<Product> GetAll() {return (List<Product>) repo.findAll(); }

    public void add(Product newProduct)
    {
        repo.save(newProduct); }
}
