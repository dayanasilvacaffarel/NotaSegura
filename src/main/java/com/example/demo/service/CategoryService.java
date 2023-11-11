package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.exceptions.BadRequetsException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Category addCategory(Category category){

        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Optional<Category> findcategoryById(Long id){
        return categoryRepository.findById(id);
    }

    public Category updateCategory(Category category) throws BadRequetsException{
        Optional<Category> categorySearched = findcategoryById(category.getId());
        if (categorySearched.isPresent()){
            return categoryRepository.save(category);
        } else {
            throw  new BadRequetsException("Could not update category with id: " +category.getId()+ " and name" + category.getName());
        }
    }

    public void deletecategory(Long id) throws ResourceNotFoundException{
        Optional<Category> categorySearched = findcategoryById(id);
        if (categorySearched.isPresent()){
            categoryRepository.deleteById(id);
        }else {
            throw new ResourceNotFoundException("The Category with the id: " + id + " does not exist.");
        }
    }
}