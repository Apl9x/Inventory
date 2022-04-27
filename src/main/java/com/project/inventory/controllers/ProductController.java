package com.project.inventory.controllers;

import com.project.inventory.entities.Product;
import com.project.inventory.services.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return this.productService.getAll();
    }

    @GetMapping("/products/{id}")
    public Optional<Product> getProduct(@PathVariable("id") long id){
        return this.productService.get(id);
    }

    @PostMapping("/products")
    public Product addProduct(@Valid @RequestBody Product p){
        return this.productService.add(p);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@Valid @RequestBody Product p, @PathVariable long id){
        return this.productService.update(p,id);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable("id") long id){
        this.productService.delete(id);
    }

}
