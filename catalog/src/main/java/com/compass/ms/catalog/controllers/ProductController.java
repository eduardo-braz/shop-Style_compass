package com.compass.ms.catalog.controllers;

import com.compass.ms.catalog.DTOs.ProductDTO;
import com.compass.ms.catalog.DTOs.ProductFormDTO;
import com.compass.ms.catalog.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/products")
public class ProductController implements ProductControllerInterface{

    @Autowired
    private ProductService productService;

    @Autowired
    ModelMapper modelMapper;

    @Override
    @PostMapping
    public ResponseEntity<ProductDTO> save(@RequestBody @Valid ProductFormDTO form){
        ProductDTO saved = this.productService.save(form);
        return new ResponseEntity<>(modelMapper.map(saved, ProductDTO.class), HttpStatus.CREATED);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        return new ResponseEntity<>(this.productService.findAll(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable String id){
        Optional<ProductDTO> found = this.productService.findById(id);
        if (found.isPresent())
            return new ResponseEntity<>(found.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid ProductFormDTO form, @PathVariable String id){
        Optional<ProductDTO> updated = this.productService.update(form, id);
        if (updated.isPresent())
            return new ResponseEntity<>(updated.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        return new ResponseEntity<>(this.productService.delete(id));
    }

}
