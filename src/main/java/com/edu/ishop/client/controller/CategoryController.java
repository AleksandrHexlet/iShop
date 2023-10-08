package com.edu.ishop.client.controller;

import com.edu.ishop.helpers.entity.Category;
import com.edu.ishop.client.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("/01")
    public List<Category> getCategory01(){
        List<Category> categoryList = categoryService.getCategory();
        return categoryList;
    }
//
//    @GetMapping("/02")
//    public Iterable<Category> getCategory02(){
//        return null;
//    }
//
//    @GetMapping("/03")
//    public @ResponseBody byte[] getCategory03(){
//        return new byte[1024];
//    }
//
//    @GetMapping("/04")
//    public Category getCategory04(){
//        return new Category(123,"nameCat","http://");
//    }
//    @GetMapping("/05")
//    public ResponseEntity<Category> getCategory05(){
//        return new ResponseEntity<Category>(new Category(123,"nameCat","http://"), HttpStatus.ACCEPTED);
//    }
//
    @GetMapping("/06")

    public Iterable<Category> getCategory06(@RequestParam(name = "uuid") int id,@RequestParam String name, @RequestParam String Surname){
        return null;
    }
//    @GetMapping("/07/{name}")
//    public @ResponseBody String getCategory07(@PathVariable String name){
//        return name;
//    }
//
//    @GetMapping("/08/{product}")
//    public Iterable<Category> getCategory06(@PathVariable String product){
//        // тут логика из сервиса categoryService
//        return null;
//    }
//
    @PostMapping ("/01")
    public Iterable<Category> postCategory01(@RequestBody Category category){
        return null;
    }

    @PostMapping ("/02")
    public Iterable<Category> postCategory02(@RequestParam int login){
        return null;
    }


}
