package com.ecommerce.Ecommerce.Controller;


import com.ecommerce.Ecommerce.Models.Category;
import com.ecommerce.Ecommerce.Service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// Used to handling web request and return view (MVC), usually rendering web pages
// although you have to add @ResponseBody to all method for return data.
@RestController // it is used for restfull api, Directly serialize object to JSON, where you want to retrun data not view.
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<Category> getAllCategory(){
        return categoryService.getAllCategories();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody Category category)
    {
        return categoryService.addCategories(category);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody Category category)
    {
        return categoryService.updateCategory(id, category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        return categoryService.deleteCategory(id);
    }






}
