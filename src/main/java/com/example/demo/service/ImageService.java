package com.example.demo.service;

import com.example.demo.entity.Image;
import com.example.demo.entity.Product;
import com.example.demo.exceptions.BadRequetsException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ProductService productService;

    public Image addImage(Image image){

        Optional<Product> productoBuscado=productService.findProductById(image.getProduct().getId());
        image.setProduct(productoBuscado.get());

        return imageRepository.save(image);
    }

    public List<Image> getAllImages(){
        return  imageRepository.findAll();
    }
    public Optional<Image> findById(Long id){
        return imageRepository.findById(id);
    }
    public Image updateImage(Image image)throws BadRequetsException {
        Optional<Image> imagenBuscada = findById(image.getId());
        if (imagenBuscada.isPresent()){
            return imageRepository.save(image);
        }
        else {
            throw new BadRequetsException("No se pudo actualizar la imagen con id : "+image.getId());
        }

    }

    public void deleteImage(Long id) throws ResourceNotFoundException {
        Optional<Image> imagenBuscada = findById(id);
        if (imagenBuscada.isPresent()){
            imageRepository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("No existe la imagen con id : "+id+" no se puede borrar");
        }

    }
}
