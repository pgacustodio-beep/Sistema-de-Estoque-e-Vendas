package com.mycompany.estoque.sistema.service;

import com.mycompany.estoque.sistema.dao.UsuarioDAO;
import com.mycompany.estoque.sistema.model.Usuario;

public class UsuarioService {

    private UsuarioDAO dao = new UsuarioDAO();

    public Usuario login(String login, String senha) {

        if (login.isEmpty() || senha.isEmpty()) {
            throw new RuntimeException("Preencha todos os campos");
        }

        Usuario usuario = dao.autenticar(login, senha);

        if (usuario == null) {
            throw new RuntimeException("Login ou senha inválidos");
        }

        return usuario;
    }
}