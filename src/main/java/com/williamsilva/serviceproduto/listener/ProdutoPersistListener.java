package com.williamsilva.serviceproduto.listener;

import com.williamsilva.serviceproduto.event.ProdutoPersistEvent;
import com.williamsilva.serviceproduto.model.Produto;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ProdutoPersistListener implements ApplicationListener<ProdutoPersistEvent> {

    @Override
    public void onApplicationEvent(ProdutoPersistEvent event) {
        Produto produto = event.getProduto();
        System.out.println(produto.getDescricao());
    }
}
