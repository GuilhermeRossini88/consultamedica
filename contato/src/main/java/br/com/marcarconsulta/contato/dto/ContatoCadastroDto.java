package br.com.marcarconsulta.contato.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ContatoCadastroDto(

    Long id,

    @NotBlank(message = "Nome do contato é obrigatório.")
    String nome,

    @Column(name = "data_nascimento")
    @NotNull(message = "Data de Nascimento é obrigatória.")
    LocalDate dataNascimento,
    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "O e-mail está com formato inválido.")
    String email,

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6,max = 10,message = "A senha deve ter no mínimo 6 e no máximo 10 caracteres.")
    String senha

) {
}
