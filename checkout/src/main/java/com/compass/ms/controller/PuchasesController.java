package com.compass.ms.controller;


import com.compass.ms.DTO.PurchasesDTO;
import com.compass.ms.DTO.PurchasesFormDTO;
import com.compass.ms.service.PurchasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/purchases")
public class PuchasesController {

    @Autowired
    PurchasesService purchasesService;

    @PostMapping
    public ResponseEntity<PurchasesDTO> save(@RequestBody @Valid PurchasesFormDTO body){
        this.purchasesService.save(body);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
