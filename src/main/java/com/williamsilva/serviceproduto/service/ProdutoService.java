package com.williamsilva.serviceproduto.service;

import com.williamsilva.serviceproduto.model.Produto;

public interface ProdutoService {

    Produto save(Produto produto);

    Produto one(Long produtoId);

    void delete(Long id);

    Produto update(Produto produto);
}
