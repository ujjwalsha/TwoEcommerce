package com.ecommerce.Ecommerce.Controller;


import com.cloudinary.api.exceptions.ApiException;
import com.ecommerce.Ecommerce.Config.AppConstants;
import com.ecommerce.Ecommerce.Payload.CategoryDTO;
import com.ecommerce.Ecommerce.Payload.CategoryResponse;
import com.ecommerce.Ecommerce.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    //learning purpose
    @GetMapping("/echo")
    public ResponseEntity<String> echoMessage(@RequestParam(name = "message" , required = false) String message)
    {
        return new ResponseEntity<>("Echo message" + message, HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<CategoryResponse> getAllCategory(@RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                           @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                           @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_CATEGORIES_BY, required = false) String sortBy,
                                                           @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder) throws ApiException {
        CategoryResponse categoryResponse = categoryService.getAllCategories(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CategoryDTO> addCategory(@Valid @RequestBody CategoryDTO categoryDTO) throws ApiException {
        CategoryDTO savedCategoryDTO = categoryService.addCategories(categoryDTO);
        return new ResponseEntity<>(savedCategoryDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryDTO categoryDTO)
    {
        CategoryDTO savedCategory = categoryService.updateCategory(id, categoryDTO);
        return new ResponseEntity<>(savedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) throws ApiException {

        CategoryDTO deletedCategory = categoryService.deleteCategory(id);


        return new ResponseEntity<>(deletedCategory, HttpStatus.OK);
    }

}
