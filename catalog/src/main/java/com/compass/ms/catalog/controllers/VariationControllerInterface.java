package com.compass.ms.catalog.controllers;


import com.compass.ms.catalog.DTOs.ProductDTO;
import com.compass.ms.catalog.DTOs.VariationDTO;
import com.compass.ms.catalog.DTOs.VariationFormDTO;
import com.compass.ms.catalog.exceptions.InvalidOperationException;
import com.compass.ms.catalog.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Variation Controller", description = "Controller para gerenciamento de variações de produtos.")
public interface VariationControllerInterface {


    @RequestBody( content = @Content(schema = @Schema(implementation = VariationDTO.class),
            mediaType = "application/json", examples = @ExampleObject(value = exampleForm)))
    @Operation(summary = "Cadastrar variação", description = "Cadastra uma nova variação")
    @ApiResponse(responseCode = "201", description = "Variação cadastrada com sucesso",
            content = @Content(schema = @Schema(implementation = VariationDTO.class), mediaType = "application/json",
                    examples = @ExampleObject(value = responseBodyOK)))
    @ApiResponse(responseCode = "400", description = "Parâmetro(s) inválido(s)", content =
    @Content(mediaType = "aplication/json",
            examples = @ExampleObject(value = responseBodyBadRequest)))
    @PostMapping
    public ResponseEntity<VariationDTO> save(@RequestBody @Valid VariationFormDTO form)
            throws InvalidOperationException;

    @RequestBody(content = @Content(schema = @Schema(implementation = VariationDTO.class), mediaType = "application/json",
            examples = @ExampleObject(value = exampleForm)))
    @Operation(summary = "Atualiza variação", description = "Atualiza os dados da variação com o id especificado")
    @ApiResponse(responseCode = "200", description = "Variação alterada com sucesso",
            content = @Content(schema = @Schema(implementation = VariationDTO.class), mediaType = "application/json",
                    examples = @ExampleObject(value = responseBodyOK)))
    @ApiResponse(responseCode = "400", description = "Parâmetro(s) inválido(s)", content =
    @Content(mediaType = "aplication/json",
            examples = @ExampleObject(value = responseBodyBadRequest)))
    @ApiResponse(responseCode = "404", description = "Variação não encontrada", content = @Content)
    @PutMapping("/{id}")
    public ResponseEntity<VariationDTO> update(@RequestBody @Valid VariationFormDTO form, @PathVariable String id)
            throws InvalidOperationException;


    @Operation(summary = "Deleta variação", description = "Deleta a variação de {id} salvo no banco")
    @ApiResponse(responseCode = "200", description = "Variação deletada", content = @Content)
    @ApiResponse(responseCode = "404", description = "Variação não encontrada", content = @Content)
    @DeleteMapping("{id}")
    public ResponseEntity<VariationDTO> delete(@PathVariable String id);

    @Operation(summary = "Busca produto por id de variação",
            description = "Busca o produto que possui a variação de {id} salvo no banco")
    @ApiResponse(responseCode = "200", description = "Produto encontrado",
            content = @Content(schema = @Schema(implementation = ProductDTO.class), mediaType = "application/json",
                    examples = @ExampleObject(value = productByVariationID)))
    @ApiResponse(responseCode = "404", description = "Variação não encontrada", content = @Content)
    @GetMapping("{id}")
    public ResponseEntity<ProductDTO> findProductByIdVariation(@PathVariable String id) throws NotFoundException;

    String exampleForm = "{\n"+
            "\"color\": \"Azul\",\n"+
            "\"size\": \"M\",\n"+
            "\"price\": 150.90,\n"+
            "\"quantity\": 2,\n"+
            "\"product_id\": \"624255b42bcbf85c928aada5\"\n"+
    "}";


    String responseBodyOK = "{\n" +
            "    \"id\": \"6245a0bc8c13e62ab59340c6\",\n" +
            "    \"color\": \"Azul\",\n" +
            "    \"size\": \"M\",\n" +
            "    \"price\": 150.90,\n" +
            "    \"quantity\": 2\n" +
            "}";

    String responseBodyBadRequest = "{" +
            "\"field\": \"color\",\n" +
            "\"error\": \"Campo Cor não pode estar vazio ou ser nulo!\"\n" +
            "}";;

    String productByVariationID = "{\n" +
            "        \"id\": \"624255b42bcbf85c928aada5\",\n" +
            "        \"name\": \"Camisa Oficial do Flamengo\",\n" +
            "        \"description\": \"A camisa pra você que é rubro-negro\",\n" +
            "        \"active\": true,\n" +
            "        \"variations\": [\n" +
            "            {\n" +
            "                \"id\": \"62435f8dcafab6581f01ce7c\",\n" +
            "                \"color\": \"Padrão\",\n" +
            "                \"size\": \"M\",\n" +
            "                \"price\": 249.99,\n" +
            "                \"quantity\": 10\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"6244950a39b4292ce7e97dcd\",\n" +
            "                \"color\": \"Vermelha\",\n" +
            "                \"size\": \"M\",\n" +
            "                \"price\": 169.90,\n" +
            "                \"quantity\": 2\n" +
            "            }\n" +
            "        ]\n" +
            "    }";
}
