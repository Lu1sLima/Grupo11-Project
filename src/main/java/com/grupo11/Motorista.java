package com.grupo11;


public class Motorista{
    private String cpf;
    private String nome;
    private Veiculo veiculo;
    private FormaPagamento formaPagamento;

    public Motorista(String cpf, String nome, Veiculo veiculo, FormaPagamento formaPagamento){
        this.cpf = cpf;
        this.nome = nome;
        this.veiculo = veiculo;
        this.formaPagamento = formaPagamento;
    }
    public String getCPF(){
        return this.cpf;
    }

    public Veiculo getVeiculo(){
        return this.veiculo;
    }

    public String getNome(){
        return this.nome;
    }

    public FormaPagamento getFormaPagamento(){
        return this.formaPagamento;
    }


    public String toString(){
        return "Motorista: "+this.nome+"\n"+
        "CPF: "+this.cpf+"\n"+
        "Veiculo: "+this.veiculo+"\n"+
        "Formas de Pagamento: "+this.formaPagamento;
    }
}