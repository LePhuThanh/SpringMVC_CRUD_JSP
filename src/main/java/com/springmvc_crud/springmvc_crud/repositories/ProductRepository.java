package com.springmvc_crud.springmvc_crud.repositories;

import com.springmvc_crud.springmvc_crud.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {
    Iterable<Product>findByCategoryID(String categoryID);
}
