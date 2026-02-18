package com.liciton.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data // O Lombok cria os Getters, Setters e ToString automaticamente
@Entity
@Table(name = "tb_usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome completo é obrigatório")
    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;

    @NotBlank(message = "O CPF é obrigatório")
    @Column(unique = true, nullable = false)
    private String cpf;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    private String senha; // Será armazenada criptografada

    @Column(name = "telefone")
    private String telefone; // Para WhatsApp

    @Enumerated(EnumType.STRING)
    @Column(name = "perfil", nullable = false)
    private PerfilUsuario perfil;

    @NotNull(message = "O aceite dos termos é obrigatório")
    @Column(name = "termos_aceitos")
    private Boolean termosAceitos;

    @Column(name = "empresa_dominio")
    private String empresaDominio; // Ex: "mecflux.com.br"

    @CreationTimestamp
    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;
}