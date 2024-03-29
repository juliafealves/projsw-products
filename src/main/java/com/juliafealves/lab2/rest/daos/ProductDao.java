package com.juliafealves.lab2.rest.daos;

import com.juliafealves.lab2.rest.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface ProductDao<T, ID extends Serializable> extends JpaRepository<Product, Long> {
    Product save(Product product);

    Product findById(long id);
}
