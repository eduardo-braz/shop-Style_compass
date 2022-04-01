package com.compass.ms.service;


import com.compass.ms.DTO.PurchasesDTO;
import com.compass.ms.DTO.PurchasesFormDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PurchasesServiceImpl implements PurchasesService{

    @Autowired
    ModelMapper modelMapper;

    @Override
    public PurchasesDTO save(PurchasesFormDTO body) {
        try{
            System.out.println(body.toString());
            System.out.println(body.getCart());
            return  new PurchasesDTO();
        } catch (Exception e){
            throw new RuntimeException();           // Mudar exceção
        }
    }
}
