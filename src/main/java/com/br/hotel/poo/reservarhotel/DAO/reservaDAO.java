package com.br.hotel.poo.reservarhotel.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class reservaDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/reservahotel";
    private static final String USER = "root";
    private static final String PASSWORD = "212003";

    public void fazerReserva(String destino, LocalDate checkIn, LocalDate checkOut, int adultos, int criancas, int quartos) {
        String sql = "INSERT INTO reservas (destino, check_in, check_out, adultos, criancas, quartos) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, destino);
            stmt.setDate(2, java.sql.Date.valueOf(checkIn));
            stmt.setDate(3, java.sql.Date.valueOf(checkOut));
            stmt.setInt(4, adultos);
            stmt.setInt(5, criancas);
            stmt.setInt(6, quartos);

            stmt.executeUpdate();
            System.out.println("Reserva realizada com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao fazer reserva: " + e.getMessage());
        }
    }
}
