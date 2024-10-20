package com.br.hotel.poo.reservarhotel;

public class Hotel {
    private String nome;
    private String descricao;
    private double preco;
    private int estrelas;
    private String imagem;

    public Hotel(String nome, String descricao, double preco, int estrelas, String imagem) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estrelas = estrelas;
        this.imagem = imagem;
    }



    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public int getEstrelas() {
        return estrelas;
    }

    public String getImagem() {
        return imagem;
    }
}
