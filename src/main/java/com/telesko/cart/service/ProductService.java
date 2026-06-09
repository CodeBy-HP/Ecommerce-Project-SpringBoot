package com.telesko.cart.service;

import com.telesko.cart.model.Product;
import com.telesko.cart.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getProductById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile file) throws IOException {
        product.setImageName(file.getOriginalFilename());
        product.setImageType(file.getContentType());
        product.setImageData(file.getBytes());
        return repo.save(product);
    }

    @Transactional(rollbackFor = IOException.class)
    public Product updateProduct(int id, Product newProduct, MultipartFile image) throws IOException {
        if (!repo.existsById(id)) {
            return null;
        }

        newProduct.setId(id);

        if (image != null && !image.isEmpty()) {
            newProduct.setImageName(image.getOriginalFilename());
            newProduct.setImageType(image.getContentType());
            newProduct.setImageData(image.getBytes());
        }

        return repo.save(newProduct);
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

    public List<Product> searchProducts(String keyword) {
        return repo.searchProducts(keyword);
    }
}
