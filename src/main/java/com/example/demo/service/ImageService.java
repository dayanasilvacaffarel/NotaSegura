package com.example.demo.service;

import com.example.demo.entity.Image;
import com.example.demo.entity.Product;
import com.example.demo.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;
    @Autowired
    ProductService productService;

}
