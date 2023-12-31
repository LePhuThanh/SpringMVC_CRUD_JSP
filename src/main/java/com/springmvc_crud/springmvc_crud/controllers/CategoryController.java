
package com.springmvc_crud.springmvc_crud.controllers;

import com.springmvc_crud.springmvc_crud.models.Category;
import com.springmvc_crud.springmvc_crud.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping(path = "/api/v1/categories")
//http:localhost:8080/api/v1/categories
public class CategoryController {
    @Autowired //Inject "categoryRepository" - DI // if it exits => call , opposite => self-create
    private CategoryRepository categoryRepository;
    //return name of "jsp file"
    @GetMapping()
    //Access from clients or Browser => GET; Sent from Form or Postman => POST
    public String getAllCategories(ModelMap modelMap) {
        //data sent to jsp => ModelMap
        //send data from Controller to View
        Iterable<Category> categories = categoryRepository.findAll();
        modelMap.addAttribute("categories", categories);
        System.out.println("haha");
        return "category"; //category.jsp

    }
}
