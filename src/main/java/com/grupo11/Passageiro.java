package com.grupo11;

public class Passageiro{
    
    String cpf;
    String nome;
    FormaPagamento formaPgto;
    String nroCartao;

    public Passageiro(String cpf, String nome, FormaPagamento formaPgto, String nroCartao) {
        this.cpf = cpf;
        this.nome = nome;
        this.formaPgto = formaPgto;
        this.nroCartao = nroCartao;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }


    public FormaPagamento getFormaPgto() {
        return formaPgto;
    }

  

    public String getNroCartao() {
        return nroCartao;
    }

    @Override
    public String toString() {
        return "Passageiro [cpf=" + cpf + ", formaPgto=" + formaPgto + ", nome=" + nome + ", nroCartao=" + nroCartao
                + "]";
    }

    
    


}