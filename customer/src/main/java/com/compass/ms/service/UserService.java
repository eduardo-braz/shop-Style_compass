package com.compass.ms.service;

import com.compass.ms.DTO.UserDTO;
import com.compass.ms.DTO.UserFormDTO;
import org.springframework.http.ResponseEntity;


public interface UserService {

    UserDTO save(UserFormDTO formDTO);

    ResponseEntity<UserDTO> findId(Long id);

    ResponseEntity<UserDTO> update(UserFormDTO formDTO, Long id);

}
