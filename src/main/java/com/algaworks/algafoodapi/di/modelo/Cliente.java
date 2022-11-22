package com.algaworks.algafoodapi.di.modelo;

public class Cliente {
    private String nome;

    public Cliente(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    private String email;
    private String telefone;
    private boolean ativo = false;


    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void ativar(){
        this.ativo = true;
    }
}
