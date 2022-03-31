package com.compass.ms.catalog.controllers;

import com.compass.ms.catalog.DTOs.ProductDTO;
import com.compass.ms.catalog.DTOs.VariationDTO;
import com.compass.ms.catalog.DTOs.VariationFormDTO;
import com.compass.ms.catalog.exceptions.InvalidOperationException;
import com.compass.ms.catalog.exceptions.NotFoundException;
import com.compass.ms.catalog.services.VariationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/v1/variations")
public class VariationController implements VariationControllerInterface {

    @Autowired
    private VariationService variationService;

    @Override
    @PostMapping
    public ResponseEntity<VariationDTO> save(@RequestBody @Valid VariationFormDTO form)
            throws InvalidOperationException {
        Optional<VariationDTO> saved = this.variationService.save(form);
        return new ResponseEntity<>(saved.get(), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("{id}")
    public ResponseEntity<VariationDTO> update(@RequestBody @Valid VariationFormDTO form, @PathVariable String id)
            throws InvalidOperationException {
        VariationDTO updated = this.variationService.update(form, id);
        return new ResponseEntity<>(updated, HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<VariationDTO> delete(@PathVariable String id) {
        return new ResponseEntity<>(this.variationService.delete(id));
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<ProductDTO> findProductByIdVariation(@PathVariable String id) throws NotFoundException {
        return new ResponseEntity<>(this.variationService.findById(id),
                HttpStatus.OK);
    }



}
