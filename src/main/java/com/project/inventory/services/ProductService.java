package com.project.inventory.services;

import com.project.inventory.entities.Product;
import com.project.inventory.repos.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll(){
        return (List<Product>) this.productRepository.findAll();
    }

    public Product add(Product product){
        return this.productRepository.save(product);
    }

    public Optional<Product> get(long id){
        return this.productRepository.findById(id);
    }

    public Product update(Product p, long id){
        return this.productRepository.findById(id)
                .map(product -> {
                    product.setPrice(p.getPrice());
                    product.setName(p.getName());
                    product.setQuantity(p.getQuantity());
                    return this.productRepository.save(product);
                }).orElseGet(() -> {
                    p.setId(id);
                    return this.productRepository.save(p);
                });
    }

    public void delete(long id){
        this.productRepository.deleteById(id);
    }
}
