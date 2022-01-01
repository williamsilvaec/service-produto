package com.williamsilva.serviceproduto.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.williamsilva.serviceproduto.event.ProdutoPersistEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class ProdutoPersistQueueListener implements ApplicationListener<ProdutoPersistEvent> {

    private static final Logger logger = Logger.getLogger(ProdutoPersistQueueListener.class.getName());

    private final ObjectMapper objectMapper;
    private final JmsTemplate jmsTemplate;

    public ProdutoPersistQueueListener(ObjectMapper objectMapper, JmsTemplate jmsTemplate) {
        this.objectMapper = objectMapper;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void onApplicationEvent(ProdutoPersistEvent event) {
        var produto = event.getProduto();
        try {
            var json = objectMapper.writeValueAsString(produto);
            jmsTemplate.convertAndSend("produto.persist.queue", json);
        } catch (JsonProcessingException e) {
            logger.finer(e.getMessage());
        }
    }
}
