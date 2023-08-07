package com.springmvc_crud.springmvc_crud.controllers;



import com.springmvc_crud.springmvc_crud.models.Category;
import com.springmvc_crud.springmvc_crud.models.Product;
import com.springmvc_crud.springmvc_crud.repositories.CategoryRepository;
import com.springmvc_crud.springmvc_crud.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "api/v1/products")

public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    //http:localhost:8080/api/v1/products/getProductByCategoryID/{categoryID}
    //http:localhost:8080/api/v1/products/getProductByCategoryID/C0105
    @GetMapping(value = "/getProductByCategoryID/{categoryID}")
    public String getProductByCategoryID(ModelMap modelMap, @PathVariable String categoryID) {
        Iterable<Product> products = productRepository.findByCategoryID(categoryID);
        modelMap.addAttribute("products", products);
        return "productList";
    }
    //http:localhost:8080/api/v1/products/changeCategory/${product.getProductID()}

    @GetMapping(value = "/changeCategory/{productID}")
    public String changeCategory (ModelMap modelMap, @PathVariable String productID) {
        Iterable<Category> categories = categoryRepository.findAll();
        Optional<Product> product = productRepository.findById(productID); // To get getProductName // maybe NULL
        modelMap.addAttribute("categories", categories);
        modelMap.addAttribute("product", product.get());
        return "assign";
    }

    // http:localhost:8080/api/v1/products/updateProduct/{productID}
    @PostMapping(value = "/updateProduct/{productID}")
    public String updateProduct(ModelMap modelMap,
                                @ModelAttribute("product") Product product, // update new value
                                @PathVariable String productID) {
        //check condition + update

        if(productRepository.findById(product.getProductID()).isPresent()) {

            Product foundProduct = productRepository
                    .findById(product.getProductID()).get();
            if (product.getProductName() != null) {
                foundProduct.setProductName(product.getProductName());
            }
            if(product.getCategoryID() != null) {
                foundProduct.setCategoryID(product.getCategoryID());
            }
            if(product.getDescription() != null) {
                foundProduct.setDescription(product.getDescription());
            }
            if(product.getPrice() > 0) {
                foundProduct.setPrice(product.getPrice());
            }
            productRepository.save(foundProduct);

        }
        return "redirect:/api/v1/products/getProductByCategoryID/" + product.getCategoryID();

    }
}

