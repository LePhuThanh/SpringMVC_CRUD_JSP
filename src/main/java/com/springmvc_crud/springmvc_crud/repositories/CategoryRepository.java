package com.springmvc_crud.springmvc_crud.repositories;

import com.springmvc_crud.springmvc_crud.models.Category;
import com.springmvc_crud.springmvc_crud.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, String> { // BC type of Category is String

}
