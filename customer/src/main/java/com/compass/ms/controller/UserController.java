package com.compass.ms.controller;

import com.compass.ms.DTO.UserDTO;
import com.compass.ms.DTO.UserFormDTO;
import com.compass.ms.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.media.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;


@RestController
@RequestMapping("/v1")
@OpenAPIDefinition(info = @Info(title = "MS Customer",
        description = "O MS customer tem a função de armazenar e gerenciar os dados de usuário",
        version = "v1"))
@Tag(name = "API MS Customer", description = "API para gerenciamento de usuários do micro-serviço de usuários")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    @Transactional
    public ResponseEntity<UserDTO> save(@RequestBody @Valid UserFormDTO body){
        UserDTO userDto = this.userService.save(body);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Busca usuário", description = "Busca um usuário de {id} salvo no banco")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado",
            content = @Content(schema = @Schema(implementation = UserDTO.class), mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Usuário não encontrado", content = @Content)
    @GetMapping("/users/{id}")
    @Transactional
    public ResponseEntity<UserDTO> findId(@PathVariable Long id){
        return this.userService.findId(id);
    }


    @PutMapping("/users/{id}")
    @Transactional
    public ResponseEntity<UserDTO> update(@RequestBody @Valid UserFormDTO body, @PathVariable Long id){
        return this.userService.update(body, id);
    }

}
