package com.br.hotel.poo.reservarhotel;

import java.util.ArrayList;
import java.util.List;

public class UsuarioManager {
    private static List<Usuario> usuarios = new ArrayList<>();

    public static void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public boolean verificarCredenciais(String email, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                return true; // Credenciais corretas
            }
        }
        return false; // Credenciais incorretas
    }
}
