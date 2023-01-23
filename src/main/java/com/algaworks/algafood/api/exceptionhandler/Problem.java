package com.algaworks.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class Problem {

    private Integer status;
    private String type;
    private String title;
    private String detail;

    private String userMessage;
    private LocalDateTime  localtime;
    private List<Field> objects;

    @Getter
    @Setter
    @Builder
    public static class Field{
        private String name;
        private String userMessage;

        public Field(String name, String userMessage) {
            this.name = name;
            this.userMessage = userMessage;
        }
        public Field() {
        }

    }

}
