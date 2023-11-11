package com.example.demo.controller;

import com.example.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
@RequestMapping("/images")
public class ImageController {

    @Autowired
    ImageService imageService;

}
