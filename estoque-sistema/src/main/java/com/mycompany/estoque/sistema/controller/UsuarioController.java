package com.mycompany.estoque.sistema.controller;

import com.mycompany.estoque.sistema.model.Usuario;
import com.mycompany.estoque.sistema.service.UsuarioService;

public class UsuarioController {

    private UsuarioService service = new UsuarioService();

    public Usuario login(String login, String senha) {
        return service.login(login, senha);
    }
}