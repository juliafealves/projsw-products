package com.juliafealves.lab2.rest.services;

import com.juliafealves.lab2.rest.daos.ProductDao;
import com.juliafealves.lab2.exceptions.product.ProductNotFoundException;
import com.juliafealves.lab2.rest.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductDao productDao;

    ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Product create(Product product) {
        return productDao.save(product);
    }

    public Product update(Product productToUpdate) throws ProductNotFoundException {
        Product product = productDao.findById(productToUpdate.getId());

        if (product == null) {
            throw new ProductNotFoundException("Could not update. The product does not exist.");
        }

        return productDao.save(productToUpdate);
    }

    public void delete(long id) {
        productDao.delete(id);
    }

    public Product findById(long id) {
        return productDao.findById(id);
    }

    public List findAll() {
        return productDao.findAll();
    }
}
