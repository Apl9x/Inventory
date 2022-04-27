package com.project.inventory.controllers;

import com.project.inventory.entities.Product;
import com.project.inventory.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Inventory", description = "The Inventory API")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    @Operation(summary = "Find all Products")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public List<Product> getAllProducts(){
        return this.productService.getAll();
    }

    @GetMapping("/products/{id}")
    @Operation(summary = "Find a Product with the id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public Optional<Product> getProduct(@PathVariable("id") long id){
        return this.productService.get(id);
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add a Product")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "CREATED")})
    public Product addProduct(@Valid @RequestBody Product p){
        return this.productService.add(p);
    }

    @PutMapping("/products/{id}")
    @Operation(summary = "Update a Product with the id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public Product updateProduct(@Valid @RequestBody Product p, @PathVariable long id){
        return this.productService.update(p,id);
    }

    @DeleteMapping("/products/{id}")
    @Operation(summary = "Delete a Product with the id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public void deleteProduct(@PathVariable("id") long id){
        this.productService.delete(id);
    }

}
