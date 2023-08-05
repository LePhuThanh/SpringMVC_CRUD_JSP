
package com.springmvc_crud.springmvc_crud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequestMapping(path = "/api/v1/categories")
//http:localhost:8080/api/v1/categories
public class CategoryController {
    //return name of "jsp file"
    @GetMapping(value="/")
    //Access from clients or Browser => GET; Sent from Form or Postman => POST
    public String getAllCategories(ModelMap modelMap) {
        //data sent to jsp => ModelMap
        //send data from Controller to View
//        modelMap.addAttribute("name", "Thanh");
//        modelMap.addAttribute("name", 18);

        System.out.println("haha");
        return "category"; //category.jsp

    }
}
