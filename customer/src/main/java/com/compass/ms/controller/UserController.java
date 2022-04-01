package com.compass.ms.controller;

import com.compass.ms.DTO.UserDTO;
import com.compass.ms.DTO.UserFormDTO;
import com.compass.ms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RequestMapping("/v1")
@RestController
public class UserController implements UserControllerInterface{

    @Autowired
    private UserService userService;

    @Override
    @PostMapping("/users")
    @Transactional
    public ResponseEntity<UserDTO> save(@RequestBody @Valid UserFormDTO body){
        UserDTO userDto = this.userService.save(body);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/users/{id}")
    @Transactional
    public ResponseEntity<UserDTO> findId(@PathVariable Long id){
        return this.userService.findId(id);
    }


    @Override
    @PutMapping("/users/{id}")
    @Transactional
    public ResponseEntity<UserDTO> update(@RequestBody @Valid UserFormDTO body, @PathVariable Long id){
        return this.userService.update(body, id);
    }

}
