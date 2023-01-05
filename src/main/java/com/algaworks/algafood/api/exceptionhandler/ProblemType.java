package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
    JSON_INVALIDO("/conteudo-invalido","Conteúdo inválido"),
    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada","Entidade não encontrada"),
    ENTIDADE_EM_USO("/entidade-em-uso","Entidade em Uso"),
    ERRO_NEGOCIO("/erro-negocio","Regra de negócio violada");

    private String title;
    private String uri;

    ProblemType(String path, String title){
        this.uri = "https://algafood.com.br" + path;
        this.title = title;
    }
}
