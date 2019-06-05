package com.juliafealves.lab2.rest.controllers;

import com.juliafealves.lab2.exceptions.product.ProductNotFoundException;
import com.juliafealves.lab2.rest.models.Product;
import com.juliafealves.lab2.rest.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductController {
    private ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Product> findById(@PathVariable long id) {
        Product product = productService.findById(id);

        if (product == null) {
            throw new ProductNotFoundException("Product not found.");
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping(value = "/")
    @ResponseBody
    public ResponseEntity<List<Product>> findAll() {
        try {
            return new ResponseEntity<List<Product>>(productService.findAll(), HttpStatus.OK);
        } catch (Exception exception) {
            throw new InternalError("Error in List Products.");
        }
    }

    @PostMapping(value = "/")
    @ResponseBody
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product newProduct = productService.create(product);

        if (newProduct == null) {
            throw new InternalError("Something went wrong.");
        }

        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        try {
            productService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception exception) {
            throw new InternalError("Something went wrong.");
        }
    }

    @PutMapping(value = "/")
    public ResponseEntity<Product> update(@RequestBody Product product) {
        try {
            Product updated = productService.update(product);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception exception) {
            throw new InternalError("Something went wrong.");
        }
    }
}
