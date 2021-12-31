package com.williamsilva.serviceproduto.service;

import com.williamsilva.serviceproduto.model.Produto;

public interface ProdutoService {

    Produto inserir(Produto produto);

    Produto one(Long produtoId);
}
