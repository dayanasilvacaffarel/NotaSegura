package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Policy;
import com.example.demo.entity.Product;
import com.example.demo.exceptions.BadRequetsException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryService categoryService;

    @Autowired
    PolicyService policyService;


    public Product addProduct (Product product){
        Optional<Category> categoriaBuscada = categoryService.findcategoryById(product.getCategory().getId());
        product.setCategory(categoriaBuscada.get());

        Set<Policy> policies =  new HashSet<>();
        for (Policy policy : product.getPolicies()){
            Optional<Policy> policyBuscada = policyService.findPolicyById(policy.getId());
            policies.add(policyBuscada.get());
        }
        product.setPolicies(policies);
        return productRepository.save(product);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public Optional<Product> findProductById(Long id){
        return productRepository.findById(id);
    }
//    public Optional<List<Product>> findAllByCategory(Long id) {
//        return productRepository.findAllByCategories_Id(id);
//    }


    public Product updateProduct(Product product) throws BadRequetsException{
        Optional<Product> productBuscado = findProductById(product.getId());
        Optional<Category> categoryBuscada = categoryService.findcategoryById(product.getCategory().getId());
        if (productBuscado.isPresent() && categoryBuscada.isPresent()){
            return productRepository.save(product);
        }else {
            throw new BadRequetsException("It is not possible to update the Product with the id: "+product.getId()+ "because the necessary data to make the request is not found.");
        }
    }

    public void deletePdoductById(Long id) throws ResourceNotFoundException {
        Optional<Product> productBuscado = findProductById(id);
        if (productBuscado.isPresent()){
            productRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("It is not possible delete the Product with the id: "+id);
        }
    }

}
