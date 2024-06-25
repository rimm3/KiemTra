package com.example.KiemTra2.controller;

import com.example.KiemTra2.entity.Product;
import com.example.KiemTra2.repository.CategoryRepository;
import com.example.KiemTra2.repository.ProductRepository;
import com.example.KiemTra2.service.ImageService;
import com.example.KiemTra2.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RequestMapping("/products")
@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ImageService imageService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("")
    public String index(Model model)
    {
        model.addAttribute("listproduct", productService.GetAll());
        return "products/index";
    }

    @GetMapping("/create")
    public String create(Model model)
    {
        model.addAttribute("product", new Product());
        return "products/create";
    }

    @PostMapping("/create")
    public String create(@Valid Product newProduct,
                         @RequestParam("imageProduct") MultipartFile imageProduct,
                         BindingResult result,
                         Model model) {
        // Kiểm tra lỗi validation
        if (result.hasErrors()) {
            // Thêm sản phẩm vào model để hiển thị lại form
            model.addAttribute("product", newProduct);
            return "product-form";
        }

        // Lưu sản phẩm mới vào database
        try {
            productRepository.save(newProduct);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Lỗi khi lưu sản phẩm: " + e.getMessage());
            return "product-form";
        }

        // Upload hình ảnh sản phẩm (nếu có)
        if (!imageProduct.isEmpty()) {
            try {
                // Lưu file hình ảnh vào đường dẫn trên server
                String uploadDir = "src/main/resources/static/images/";
                Path uploadPath = Paths.get(uploadDir);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                String fileName = StringUtils.cleanPath(imageProduct.getOriginalFilename());
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(imageProduct.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                // Lưu đường dẫn hình ảnh vào sản phẩm
                newProduct.setImage("/images/" + fileName);
                productRepository.save(newProduct); // lưu lại sản phẩm để cập nhật đường dẫn hình ảnh
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Chuyển hướng về trang danh sách sản phẩm
        return "redirect:/products";
    }
}
