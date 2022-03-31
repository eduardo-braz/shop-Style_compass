package com.compass.ms.catalog.controllers;

import com.compass.ms.catalog.DTOs.CategoryDTO;
import com.compass.ms.catalog.DTOs.CategoryFormDTO;
import com.compass.ms.catalog.DTOs.ProductDTO;
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
import java.util.List;


@Tag(name = "Category Controller", description = "Controller para gerenciamento de categorias.")
public interface CategoryControllerInterface {

    @RequestBody( content = @Content(schema = @Schema(implementation = CategoryDTO.class), mediaType = "application/json",
            examples = @ExampleObject(value = exampleForm)))
    @Operation(summary = "Cadastrar categoria", description = "Cadastra uma nova categoria")
    @ApiResponse(responseCode = "201", description = "Categoria cadastrada com sucesso",
            content = @Content(schema = @Schema(implementation = CategoryDTO.class), mediaType = "application/json",
                    examples = @ExampleObject(value = exampleSingleDto)))
    @ApiResponse(responseCode = "400", description = "Parâmetro(s) inválido(s)", content =
    @Content(mediaType = "aplication/json",
            examples = @ExampleObject(value = responseBodyBadRequest)))
    @PostMapping
    public ResponseEntity<CategoryDTO> save(@RequestBody @Valid CategoryFormDTO body);

    @Operation(summary = "Deleta categoria", description = "Deleta a categoria de {id} salva no banco")
    @ApiResponse(responseCode = "200", description = "Categoria deletada", content = @Content)
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada", content = @Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id);

    @Operation(summary = "Buscas todas categorias", description = "Buscas todas as categoria salvas no banco")
    @ApiResponse(responseCode = "200", description = "Categorias localizadas com sucesso",
            content = @Content(schema = @Schema(implementation = CategoryDTO.class), mediaType = "application/json",
            examples = @ExampleObject(value = exampleListDto)))
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAll();


    @Operation(summary = "Buscas todos produtos da categoria",
            description = "Buscas todos os produtos da categoria do id informado.")
    @ApiResponse(responseCode = "200", description = "Produtos localizados com sucesso",
            content = @Content(schema = @Schema(implementation = ProductDTO.class), mediaType = "application/json",
                    examples = @ExampleObject(value = productsOfCategoryList)))
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada", content = @Content)
    @GetMapping("/{id}/products")
    public ResponseEntity<List<ProductDTO>> getProductsOfCategory(@PathVariable String id) throws NotFoundException;


    @RequestBody(content = @Content(schema = @Schema(implementation = CategoryDTO.class), mediaType = "application/json",
            examples = @ExampleObject(value = exampleForm)))
    @Operation(summary = "Atualiza categoria", description = "Atualiza os dados da categoria com o id especificado")
    @ApiResponse(responseCode = "200", description = "Categoria alterada com sucesso",
            content = @Content(schema = @Schema(implementation = CategoryDTO.class), mediaType = "application/json",
                    examples = @ExampleObject(value = exampleSingleDto)))
    @ApiResponse(responseCode = "400", description = "Parâmetro(s) inválido(s)", content =
    @Content(mediaType = "aplication/json",
            examples = @ExampleObject(value = responseBodyBadRequest)))
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada", content = @Content)
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@RequestBody @Valid CategoryFormDTO form, @PathVariable String id)
            throws NotFoundException;

    String exampleForm = "{\n" +
            "\"name\": \"Camisas de Futebol\",\n" +
            "\"active\": true\n" +
            "}";

    String responseBodyBadRequest = "[" +
            "{\"field\": \"name\",\n" +
            "\"error\": \"Nome não pode estar vazio ou ser nulo!\"\n" +
            "}" +
            "]";

    String exampleSingleDto= "{\n" +
            "\"id\": \"6241be7437103a76955a57c5\",\n" +
            "\"name\": \"Camisas de Futebol\",\n" +
            "\"active\": true\n" +
            "}";

    String exampleListDto= "[" +
            "{" +
            "\"id\": \"6241be7437103a76955a57c5\"," +
            "\"name\": \"Camisas de Futebol\","+
            "\"active\": true"+
            "},"+
            "{" +
            "\"id\": \"62421756263a88136a377751\"," +
            "\"name\": \"Blusas de Frio\","+
            "\"active\": true"+
            "}"+
            "]";
    String productsOfCategoryList = "[\n" +
            "    {\n" +
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
            "                \"color\": \"Branca\",\n" +
            "                \"size\": \"M\",\n" +
            "                \"price\": 169.90,\n" +
            "                \"quantity\": 2\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": \"6243769e84363a18f25f0d9a\",\n" +
            "        \"name\": \"Camiseta casual\",\n" +
            "        \"description\": \"Camiseta casual de tamanho único\",\n" +
            "        \"active\": true,\n" +
            "        \"variations\": []\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": \"62448b2b4124eb176b0dcb2d\",\n" +
            "        \"name\": \"Camisa retrô\",\n" +
            "        \"description\": \"A camisa para lembrar dos bons momentos\",\n" +
            "        \"active\": false,\n" +
            "        \"variations\": []\n" +
            "    }\n"+
            "]";
}

