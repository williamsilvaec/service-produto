package com.williamsilva.serviceproduto.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.williamsilva.serviceproduto.http.data.request.ProdutoPersistDto;
import com.williamsilva.serviceproduto.model.Produto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface ProdutoController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Produto inserir(@RequestBody @Valid ProdutoPersistDto dto);

    @Operation(summary = "Retorna o produto correspondente ao identificador recuperado")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "codigo": "X_100",
                                                "mensagem": "Produto de código 542 não encontrado",
                                                "documentacao": null
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping("{id}")
    Produto buscar(@PathVariable("id") Long produtoId);

    @PatchMapping("{id}")
    Produto update(@PathVariable("id") Long id, @RequestBody JsonPatch patch) throws JsonPatchException, JsonProcessingException;

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") Long id);

    @PutMapping("{id}")
    Produto updateAll(@PathVariable("id") Long id, @RequestBody @Valid ProdutoPersistDto dto);
}
