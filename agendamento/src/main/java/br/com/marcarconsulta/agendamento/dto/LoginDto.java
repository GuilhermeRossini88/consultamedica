package br.com.marcarconsulta.agendamento.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDto(
        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "O e-mail não é válido")
        String email,
        @NotBlank(message = "A senha do usuário é obrigatória")
        @Size(min = 6, max = 20, message = "A senha deve conter entre 6 a 20 caracteres")
        String senha
) {
}
