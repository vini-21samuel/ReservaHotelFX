package com.br.hotel.poo.reservarhotel.DAO;

import com.br.hotel.poo.reservarhotel.Hospede;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HospedeDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/reservahotel";
    private static final String USER = "root";
    private static final String PASSWORD = "212003";

    // Método para buscar todos os hóspedes do banco de dados
    public List<Hospede> buscarTodosHospedes() {
        List<Hospede> hospedes = new ArrayList<>();

        String query = "SELECT id, nome, email, reservas, hotel FROM hospedes"; // Remova check_in

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                int reservas = rs.getInt("reservas");
                String nomeHotel = rs.getString("hotel");

                Hospede hospede = new Hospede(id, nome, email, reservas, nomeHotel);
                hospedes.add(hospede);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hospedes;
    }


    // Método para adicionar um novo hóspede ao banco de dados
    public void adicionarHospede(String nome, int reservas, String hotel) {
        String sql = "INSERT INTO hospedes (nome, reservas, hotel) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nome);
            pstmt.setInt(2, reservas);
            pstmt.setString(3, hotel);
            pstmt.executeUpdate(); // Executa a inserção

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerHospede(Hospede hospede) {
        String sql = "DELETE FROM hospedes WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, hospede.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Adicione aqui o método atualizarHospede se necessário
    public void atualizarHospede(Hospede hospede) {
        String sql = "UPDATE hospedes SET nome = ?, reservas = ?, hotel = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, hospede.getNome());
            pstmt.setInt(2, hospede.getReservas());
            pstmt.setString(3, hospede.getHotel());
            pstmt.setInt(4, hospede.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
