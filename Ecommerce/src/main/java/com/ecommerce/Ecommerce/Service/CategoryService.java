package com.ecommerce.Ecommerce.Service;


import com.ecommerce.Ecommerce.Models.Category;
import com.ecommerce.Ecommerce.Repository.CategoryRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service  //it used to indicate that applied service layer that contains business logic of an application
public class CategoryService {

    private final CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo)
    {
        this.categoryRepo = categoryRepo;
    }


    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public ResponseEntity<?> addCategories(Category category) {

        Optional<Category> existCate = categoryRepo.findByName(category.getName());

        if(existCate.isPresent())
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        categoryRepo.save(category);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> updateCategory(Long id, Category category) {

        Optional<Category> existCate = categoryRepo.findByName(category.getName());

        if(existCate.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Category categoryName = existCate.get();

        categoryName.setName(category.getName());

        return new ResponseEntity<>(HttpStatus.OK);
    }


    public ResponseEntity<?> deleteCategory(Long id) {

        if(categoryRepo.existsById(id))
        {
            categoryRepo.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("deleted successfully.");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
    }
}
