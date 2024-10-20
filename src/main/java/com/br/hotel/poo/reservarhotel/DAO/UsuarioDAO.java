package com.br.hotel.poo.reservarhotel.DAO;

import com.br.hotel.poo.reservarhotel.DataBaseconnection.DBconnection;
import com.br.hotel.poo.reservarhotel.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO {

    public void cadastrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, sobrenome, cpf, email, senha) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getSobrenome());
            statement.setString(3, usuario.getCpf());
            statement.setString(4, usuario.getEmail());
            statement.setString(5, usuario.getSenha());

            statement.executeUpdate(); // Executa a inserção

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }
}