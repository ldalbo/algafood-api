package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
    PARAMETRO_INVALIDO("/parametro-invalido","Parâmetro inválido, revise o endpoint"),
    JSON_INVALIDO("/conteudo-invalido","Conteúdo inválido "),
    RECURSO_NAO_ENCONTRADO("/entidade-nao-encontrada","Entidade não encontrada"),
    ENTIDADE_EM_USO("/entidade-em-uso","Entidade em Uso"),
    ERRO_INESPERADO("/erro-inesperado","Erro Inesperado"),
    ERRO_NEGOCIO("/erro-negocio","Regra de negócio violada"),
    DADOS_INVALIDOS("/dados-invalidos","Dados inválidos");

    private String title;
    private String uri;

    ProblemType(String path, String title){
        this.uri = "https://algafood.com.br" + path;
        this.title = title;
    }
}