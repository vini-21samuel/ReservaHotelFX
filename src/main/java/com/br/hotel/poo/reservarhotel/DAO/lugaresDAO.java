package com.br.hotel.poo.reservarhotel.DAO;

import com.br.hotel.poo.reservarhotel.DataBaseconnection.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class lugaresDAO {

    public List<String> buscarEstados(String busca) {
        List<String> estados = new ArrayList<>();
        String sql = "SELECT nome FROM estados WHERE nome LIKE ?";

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, busca + "%"); // "%" permite buscar por prefixo
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                estados.add(resultSet.getString("nome"));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar estados: " + e.getMessage());
        }

        return estados;
    }
}
