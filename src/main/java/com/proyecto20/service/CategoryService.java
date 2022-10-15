package com.proyecto20.service;


import com.proyecto20.model.Category;
import com.proyecto20.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;


    public Category create(Category category) {
        if (category.getId() == null){
            return categoryRepository.save(category);
        }else{
            Optional<Category> categoryNew = getCategory(category.getId());
            if (categoryNew.isEmpty()){
                return categoryRepository.save(category);
            }else {
                return category;
            }
        }
    }


    public Optional<Category> getCategory(Integer id) {
        return categoryRepository.findById(id);
    }


    public List<Category> categories() {
        return (List<Category>) categoryRepository.findAll();
    }


    public Category update(Category category) {
        if (category != null && category.getId() != null){
            if (categoryRepository.existsById(category.getId())){
                Optional<Category> oldCategory = categoryRepository.findById(category.getId());
                Category editedCategory = oldCategory.get();
                if (category.getName() != null){
                    editedCategory.setName(category.getName());
                }
                if (category.getDescription() != null){
                    editedCategory.setDescription(category.getDescription());
                }
                if (category.getLibs() != null){
                    editedCategory.setLibs(category.getLibs());
                }
                return categoryRepository.save(editedCategory);
            }else{
                return category;
            }
        }else {
            return category;
        }
    }

    public boolean delete(Integer id) {
        if(categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
            return true;
        }else
            return true;
    }
}
