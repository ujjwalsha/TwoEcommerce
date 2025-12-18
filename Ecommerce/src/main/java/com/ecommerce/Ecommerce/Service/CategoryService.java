package com.ecommerce.Ecommerce.Service;


import com.cloudinary.api.exceptions.ApiException;
import com.ecommerce.Ecommerce.Exception.ResourceNotFoundException;
import com.ecommerce.Ecommerce.Models.Category;
import com.ecommerce.Ecommerce.Payload.CategoryDTO;
import com.ecommerce.Ecommerce.Payload.CategoryResponse;
import com.ecommerce.Ecommerce.Repository.CategoryRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service  //it used to indicate that applied service layer that contains business logic of an application
public class CategoryService {

    private final CategoryRepo categoryRepo;
    private ModelMapper modelMapper;

    @Autowired
    public CategoryService(CategoryRepo categoryRepo, ModelMapper modelMapper)
    {
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;
    }


    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) throws ApiException {

        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();


        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Category> categoryPage = categoryRepo.findAll(pageDetails);

        List<Category> categories = categoryPage.getContent();

        if(categories.isEmpty())
        {
            throw new ApiException("No category created till now");
        }

        List<CategoryDTO>  categoryDTOS = categories.stream()
                .map(Category ->modelMapper.map(Category, CategoryDTO.class))
                .toList();

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setLastPage(categoryPage.isLast());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalPage(categoryPage.getTotalPages());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());

        return categoryResponse;
    }

    public CategoryDTO addCategories(CategoryDTO categoryDTO) throws ApiException {

        Category category = modelMapper.map(categoryDTO, Category.class);
        Optional<Category> categoryFromDb = categoryRepo.findByName(categoryDTO.getName());

        if(categoryFromDb.isPresent())
        {
            throw new ApiException("Not exists");
        }

        Category savedCategory = categoryRepo.save(category);

        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {

        Category savedCategory =  categoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", id));


        Category category = modelMapper.map(categoryDTO, Category.class);

        category.setId(id);
        category.setName(categoryDTO.getName());

        savedCategory = categoryRepo.save(category);

        return modelMapper.map(savedCategory, CategoryDTO.class);
    }


    public CategoryDTO deleteCategory(Long id) throws ApiException {

        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new ApiException("Category not found"));

        categoryRepo.delete(category);

        return modelMapper.map(category, CategoryDTO.class);
    }
}
