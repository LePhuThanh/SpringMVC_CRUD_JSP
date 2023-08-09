package com.springmvc_crud.springmvc_crud.controllers;



import com.springmvc_crud.springmvc_crud.models.Category;
import com.springmvc_crud.springmvc_crud.models.Product;
import com.springmvc_crud.springmvc_crud.repositories.CategoryRepository;
import com.springmvc_crud.springmvc_crud.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        return "updateProduct";
    }

    // http:localhost:8080/api/v1/products/updateProduct/{productID}
    @PostMapping(value = "/updateProduct/{productID}")
    public String updateProduct(ModelMap modelMap,
                                @Valid @ModelAttribute("product") Product product, // update new value
                                BindingResult bindingResult, //validation
                                @PathVariable String productID) {
        //check condition + update
        if(bindingResult.hasErrors()) {
//            System.out.println("hoho");
            modelMap.addAttribute("categories", categoryRepository.findAll());
            return "updateProduct";
        }
        if(productRepository.findById(product.getProductID()).isPresent()) {

            Product foundProduct = productRepository
                    .findById(product.getProductID()).get();
            if (!product.getProductName().trim().isEmpty()) {
                foundProduct.setProductName(product.getProductName());
            }
            if(!product.getCategoryID().isEmpty()) {
                foundProduct.setCategoryID(product.getCategoryID());
            }
            //isEmpty => "" or NULL
            if(!product.getDescription().trim().isEmpty()) {
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

