package com.br.hotel.poo.reservarhotel.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListarHoteisDAO {
    private String url = "jdbc:mysql://localhost:3306/reservahotel"; // Substitua pelo seu banco de dados
    private String usuario = "root"; // Substitua pelo seu usuário
    private String senha = "212003"; // Substitua pela sua senha

    public Hotel buscarHotel(String nomeHotel) {
        String sql = "SELECT nome_hotel, avaliacao, preco FROM hoteis WHERE nome_hotel = ?";
        Hotel hotel = null;

        try (Connection connection = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nomeHotel);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome_hotel");
                int avaliacao = rs.getInt("avaliacao");
                double preco = rs.getDouble("preco");
                hotel = new Hotel(nome, avaliacao, preco);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotel;
    }

    // Classe interna para representar os detalhes do hotel
    public class Hotel {
        private String nome;
        private int avaliacao; // Estrelas
        private double preco;

        public Hotel(String nome, int avaliacao, double preco) {
            this.nome = nome;
            this.avaliacao = avaliacao;
            this.preco = preco;
        }

        public String getNome() {
            return nome;
        }

        public int getAvaliacao() {
            return avaliacao;
        }

        public double getPreco() {
            return preco;
        }
    }
}
