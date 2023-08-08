package com.algaworks.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
@ApiModel("Problema")
public class Problem {

    @ApiModelProperty(example="400")
    private Integer status;

    @ApiModelProperty(example="https://algafood.com.br/dados-invalidos")
    private String type;

    @ApiModelProperty(example="Dados inválidos")
    private String title;
    private String detail;

    private String userMessage;
    private LocalDateTime  localtime;
    @ApiModelProperty("Lista de Objetos ou campos que geraram erro")
    private List<Field> objects;

    @Getter
    @Setter
    @Builder
    @ApiModel("ObjetoProblema")
    public static class Field{
        @ApiModelProperty(example="Preço")
        private String name;
        @ApiModelProperty(example="O preço é obrigatório ")
        private String userMessage;

        public Field(String name, String userMessage) {
            this.name = name;
            this.userMessage = userMessage;
        }
        public Field() {
        }

    }

}
