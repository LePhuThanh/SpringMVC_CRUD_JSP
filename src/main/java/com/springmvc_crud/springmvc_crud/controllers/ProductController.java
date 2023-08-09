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
import java.util.UUID;

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

    @GetMapping(value = "insertProduct")
    public String insertProduct (ModelMap modelMap) {
        modelMap.addAttribute("product", new Product());
        modelMap.addAttribute("categories", categoryRepository.findAll());
        return "insertProduct";
    }
    // Method Overloading
    @PostMapping(value = "insertProduct")
    public String insertProduct (ModelMap modelMap,
                                 @Valid @ModelAttribute("product") Product product,
                                 BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "insertProduct";
        }
        try{
            // random juid => productID
            product.setProductID(UUID.randomUUID().toString());
            productRepository.save(product);
            return "redirect:/api/v1/categories";
        }catch (Exception e){
            modelMap.addAttribute("error", e.toString());
            return "insertProduct";
        }
    }

    // http:localhost:8080/api/v1/products/updateProduct/{productID}
    @PostMapping(value = "/updateProduct/{productID}")
    public String updateProduct(ModelMap modelMap,
                                @Valid @ModelAttribute("product") Product product, // update new value
                                BindingResult bindingResult, //validation
                                @PathVariable String productID) {
        //check condition + update
        Iterable<Category> categories = categoryRepository.findAll();
        if(bindingResult.hasErrors()) {
//            System.out.println("hoho");
            modelMap.addAttribute("categories", categories);
            return "updateProduct";
        }
        try {
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
        }catch (Exception e){
            return "updateProduct";
        }
        return "redirect:/api/v1/products/getProductByCategoryID/" + product.getCategoryID();

    }
    @PostMapping(value = "/deleteProduct/{productID}")
    public String deleteProduct(ModelMap modelMap,@PathVariable String productID) {
        productRepository.deleteById(productID);
        System.out.println(">>> check delete");
        return "redirect:/api/v1/categories";
    }
}
 // api/v1/products/deleteProduct/${product.getProductID()}

//api/v1/products/deleteProduct/{productID}
