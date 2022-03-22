package com.compass.ms.service;

import com.compass.ms.DTO.UserDTO;
import com.compass.ms.DTO.UserFormDTO;
import com.compass.ms.entity.User;
import com.compass.ms.exceptions.EntityExceptionResponse;
import com.compass.ms.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserDTO save(UserFormDTO formDTO) {
        Optional<User> findUser = this.userRepository.findByEmail(formDTO.getEmail());
        if (findUser.isPresent()) {
            throw new EntityExceptionResponse(HttpStatus.BAD_REQUEST,
                    "Email " + formDTO.getEmail() + " existente no banco");
        } else {
            User user = this.userRepository.save(modelMapper.map(formDTO, User.class));
            return modelMapper.map(user, UserDTO.class);
        }
    }

    @Override
    public ResponseEntity<UserDTO> findId(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(
                    modelMapper.map(user.get(), UserDTO.class),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<UserDTO> update(UserFormDTO formDTO, Long id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            Optional<User> validUser = this.userRepository.findByEmailAndId(formDTO.getEmail(), id);
            if (validUser.isPresent()) {
                // Já existe um email igual cadastrado no banco
                throw new EntityExceptionResponse(HttpStatus.BAD_REQUEST,
                        "Email " + formDTO.getEmail() + " existente no banco");
            }
            // Atualizando dados
            user.get().setActive(formDTO.getActive());
            user.get().setCpf(formDTO.getCpf());
            user.get().setBirthdate(formDTO.getBirthdate());
            user.get().setEmail(formDTO.getEmail());
            user.get().setFirstName(formDTO.getFirstName());
            user.get().setLastName(formDTO.getLastName());
            user.get().setSex(formDTO.getSex());
            // Atualizando senha
            user.get().setPassword(formDTO.getPassword());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}