package com.liciton.backend.service;

import com.liciton.backend.model.Usuario;
import com.liciton.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario cadastrar(Usuario usuario) {
        // 1. Validar se o e-mail já existe
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("Este e-mail já está cadastrado.");
        }

        // 2. Extrair o domínio do e-mail (ex: @mecflux.com.br)
        String email = usuario.getEmail();
        String dominio = email.substring(email.indexOf("@"));
        usuario.setEmpresaDominio(dominio);

        // 3. Criptografar a senha
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        // 4. Salvar no banco
        return usuarioRepository.save(usuario);
    }
}