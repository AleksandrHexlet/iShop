package com.edu.ishop.admin.controller;

import com.edu.ishop.helpers.entity.Category;
import com.edu.ishop.helpers.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/adminHTML")

public class CategoryControllerHTML {

    CategoryRepository categoryRepository;

    @Autowired

    public CategoryControllerHTML(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/category")
    public String getCategoryHTML(Category category, Model model){
    Iterable<Category> categories = categoryRepository.findAll();
    model.addAttribute("categoriesAttr",categories);
    return "CategoryWebHTML";
    };

    @PostMapping ("/category")
    public String postCategoryHTML(@Valid Category category, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "CategoryWebHTML";
        categoryRepository.save(category);
        return "redirect:/adminHTML/category";
    }
}
