package com.compass.ms.catalog.controllers;

import com.compass.ms.catalog.DTOs.ProductDTO;
import com.compass.ms.catalog.DTOs.ProductFormDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
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

@OpenAPIDefinition(info = @Info(title = "MS Catalog",
        description = "O micro-serviço Catalog tem a função de armazenar e gerenciar os produtos e categorias que " +
                "estarão disponíveis na aplicação.",
        version = "v1",
        license = @License(
                name = "Licença pública geral GNU v3", url = "https://www.gnu.org/licenses/gpl-3.0.pt-br.html"),
        contact = @Contact(name = "Contate o desenvolvedor", url = "https://compass.uol/"),
        termsOfService = "https://github.com/eduardo-braz/shop-Style_compass"
))
@Tag(name = "Product Controller", description = "Controller para gerenciamento de produtos.")
public interface ProductControllerInterface {

    @RequestBody( content = @Content(schema = @Schema(implementation = ProductDTO.class), mediaType = "application/json",
            examples = @ExampleObject(value = exampleForm)))
    @Operation(summary = "Cadastrar produto", description = "Cadastra um novo produto")
    @ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso",
            content = @Content(schema = @Schema(implementation = ProductDTO.class), mediaType = "application/json",
                    examples = @ExampleObject(value = exampleSingleDto)))
    @ApiResponse(responseCode = "400", description = "Parâmetro(s) inválido(s)", content =
    @Content(mediaType = "aplication/json",
            examples = @ExampleObject(value = responseBodyBadRequest)))
    @PostMapping
    public ResponseEntity<ProductDTO> save(@RequestBody @Valid ProductFormDTO form);


    @Operation(summary = "Buscas todos produtos", description = "Buscas todos os produto salvos no banco")
    @ApiResponse(responseCode = "200", description = "Produtos localizados com sucesso",
            content = @Content(schema = @Schema(implementation = ProductDTO.class), mediaType = "application/json",
                    examples = @ExampleObject(value = exampleListDto)))
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts();


    @Operation(summary = "Busca produto", description = "Busca o produto de {id} salvo no banco")
    @ApiResponse(responseCode = "200", description = "Produto encontrado",
            content = @Content(schema = @Schema(implementation = ProductDTO.class), mediaType = "application/json",
                    examples = @ExampleObject(value = exampleSingleDto)))
    @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content)
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable String id);


    @RequestBody(content = @Content(schema = @Schema(implementation = ProductDTO.class), mediaType = "application/json",
            examples = @ExampleObject(value = exampleForm)))
    @Operation(summary = "Atualiza produto", description = "Atualiza os dados do produto com o id especificado")
    @ApiResponse(responseCode = "200", description = "Produto alterado com sucesso",
            content = @Content(schema = @Schema(implementation = ProductDTO.class), mediaType = "application/json",
                    examples = @ExampleObject(value = exampleSingleDto)))
    @ApiResponse(responseCode = "400", description = "Parâmetro(s) inválido(s)", content =
    @Content(mediaType = "aplication/json",
            examples = @ExampleObject(value = responseBodyBadRequest)))
    @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content)
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid ProductFormDTO form, @PathVariable String id);


    @Operation(summary = "Deleta produto", description = "Deleta o produto de {id} salvo no banco")
    @ApiResponse(responseCode = "200", description = "Produto deletado", content = @Content)
    @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id);



    String exampleForm = "{\n" +
            "\"name\": \"Camisa Oficial do Fluminense\",\n" +
            "\"description\": \"A camisa pra você que é tricolor de coração\",\n" +
            "\"active\": true,\n" +
            "\"category_ids\": [\"6241be7437103a76955a57c5\",\"62421756263a88136a377751\"]\n" +
            "}";


    String responseBodyBadRequest = "[" +
            "{\"field\": \"name\",\n" +
            "\"error\": \"Nome não pode estar vazio ou ser nulo!\"\n" +
            "}" +
            "]";


    String exampleSingleDto= "{\n" +
            "    \"id\": \"6244ba51a4411103b85870ae\",\n" +
            "    \"name\": \"Camisa Oficial do Fluminense\",\n" +
            "    \"description\": \"A camisa pra você que é tricolor de coração\",\n" +
            "    \"active\": true,\n" +
            "    \"variations\": []\n" +
            "}";


    String exampleListDto= "[" +
            "{\n" +
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
            "    }," +
            "{"+
            "\"id\": \"62448b2b4124eb176b0dcb2d\"," +
            "\"name\": \"Camisa retrô\"," +
            "\"description\": \"A camisa para lembrar dos bons momentos\"," +
            "\"active\": true," +
            "\"variations\": []" +
            "}" +
            "]";

}
