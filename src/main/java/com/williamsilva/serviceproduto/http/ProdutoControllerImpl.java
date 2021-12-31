package com.williamsilva.serviceproduto.http;

import com.williamsilva.serviceproduto.http.data.request.ProdutoPersistDto;
import com.williamsilva.serviceproduto.model.Produto;
import com.williamsilva.serviceproduto.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoControllerImpl implements ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoControllerImpl(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto inserir(@RequestBody @Valid ProdutoPersistDto dto) {
        Produto produto = new Produto(dto.getDescricao(), dto.getValor());
        return produtoService.inserir(produto);
    }

    @Override
    @GetMapping("{id}")
    public Produto buscar(@PathVariable("id") Long produtoId) {
        return produtoService.one(produtoId);
    }

}
