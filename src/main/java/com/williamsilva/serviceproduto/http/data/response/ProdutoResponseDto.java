package com.williamsilva.serviceproduto.http.data.response;

public class ProdutoResponseDto {

    private Long id;
    private String descricao;

    public ProdutoResponseDto() {
    }

    public ProdutoResponseDto(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
