package com.br.hotel.poo.reservarhotel.DataBaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    private static final String URL = "jdbc:mysql://localhost:3306/reservahotel"; // Substitua pelo nome do seu banco
    private static final String USER = "root";
    private static final String PASSWORD = "212003";

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexão estabelecida com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            throw e;  // Lançando a exceção para que o chamador saiba do erro
        }
        return connection;
    }
}
