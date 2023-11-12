package com.example.demo.controller;

import com.example.demo.entity.Image;
import com.example.demo.exceptions.BadRequetsException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    ImageService imageService;

    @PostMapping
    public ResponseEntity<Image> addImage(@RequestBody Image image){
        System.out.println("controller " + image);
        return ResponseEntity.ok(imageService.addImage(image));
    }

    @GetMapping
    public ResponseEntity<List<Image>>getAllImages(){
        return ResponseEntity.ok(imageService.getAllImages());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Image> findById(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Image> imagenBuscada = imageService.findById(id);
        return imagenBuscada.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @PutMapping
    public ResponseEntity<Image> editarImagen(@RequestBody Image image) throws BadRequetsException {
        Image imagenEditada=imageService.updateImage(image);
        return ResponseEntity.ok(imagenEditada);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteImage(@PathVariable Long id ) throws ResourceNotFoundException {
        imageService.deleteImage(id);
        return ResponseEntity.ok("Imagen con id "+ id+" fue eliminada con exito. ");
    }
}
