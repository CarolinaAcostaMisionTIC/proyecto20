package com.proyecto20.controller;

import com.proyecto20.model.Category;
import com.proyecto20.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/Category")
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@RequestBody Category category){
        categoryService.create(category);
    }

    @GetMapping("/all")
    public List<Category> getCategories(){
        return categoryService.categories();
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateCategory(@RequestBody Category category){
        categoryService.update(category);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable ("id") Integer id){
        categoryService.delete(id);
    }
}
