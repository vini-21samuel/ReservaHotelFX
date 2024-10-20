package com.br.hotel.poo.reservarhotel;

public class Hospede {
    private int id;
    private String nome;
    private String email;
    private int reservas;
    private String hotel;

    // Construtor
    public Hospede(int id, String nome, String email, int reservas, String hotel) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.reservas = reservas;
        this.hotel = hotel;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getReservas() {
        return reservas;
    }

    public void setReservas(int reservas) {
        this.reservas = reservas;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }
}
