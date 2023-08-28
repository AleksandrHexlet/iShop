package com.edu.ishop.services;

import com.edu.ishop.entity.Category;
import com.edu.ishop.repository.CategoryRepository;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public List<Category>getCategory(){
        List<Category> listCategory = new ArrayList<>();
//        listCategory.add(new Category(1,"Ivan","http//01"));
//        listCategory.add(new Category(2,"Petr","http//02"));
//        listCategory.add(new Category(3,"Igor","http//03"));
        return listCategory;
    }


//    private String name;
//    private String url;
//    @ManyToOne
//    private Category parent;
//    @OneToMany
//    private List<Category> children = new ArrayList<>();

    @Bean
    public CommandLineRunner createTable(){
        return (args) -> {
      Category nonFood = new Category("NonFood","nonfood",null);
      Category food = new Category("Food","food",null);

      Category sport = new Category("Sport","sport",nonFood);
      Category electronic = new Category("Electronic","electronic",nonFood);

      Category meal = new Category("Meal","meal",food);
      Category healthyFood  = new Category("HealthyFood","healthyfood",food);

      categoryRepository.save(nonFood);
      categoryRepository.save(food);

      categoryRepository.save(sport);
      categoryRepository.save(electronic);

      categoryRepository.save(meal);
      categoryRepository.save(healthyFood);


        };
    }
}
