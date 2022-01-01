package com.williamsilva.serviceproduto.service;

import com.williamsilva.serviceproduto.event.ProdutoPersistEvent;
import com.williamsilva.serviceproduto.model.Produto;
import com.williamsilva.serviceproduto.repository.ProdutoRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.produtoRepository = produtoRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public Produto save(Produto produto) {
        Produto produtoPersist = produtoRepository.save(produto);
        applicationEventPublisher.publishEvent(new ProdutoPersistEvent(this, produtoPersist));
        return produtoPersist;
    }

    @Override
    public Produto one(Long produtoId) {
        return produtoRepository.findById(produtoId)
                .orElseThrow(() -> new NoResultException(String.format("Produto de código %d não encontrado", produtoId)));
    }

    @Override
    public void delete(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new NoResultException(String.format("Produto de código %d não encontrado", id));
        }

        produtoRepository.deleteById(id);
    }

    @Override
    public Produto update(Produto produto) {
        if (!produtoRepository.existsById(produto.getId())) {
            throw new NoResultException(String.format("Produto de código %d não encontrado", produto.getId()));
        }

        return save(produto);
    }
}
