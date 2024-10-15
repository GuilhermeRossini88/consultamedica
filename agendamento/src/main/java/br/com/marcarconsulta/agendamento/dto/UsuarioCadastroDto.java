package br.com.marcarconsulta.agendamento.dto;

import br.com.marcarconsulta.agendamento.model.UsuarioRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCadastroDto(

       Long id,
       @NotBlank(message = "O nome do usuário é obrigatório")
       String nome,
       @NotBlank(message = "O e-mail é obrigatório")
       @Email
       String email,
       @NotBlank(message = "A senha é obrigatória")
       @Size(min = 6,max = 20, message = "A senha deve conter entre 6 a 20 caracteres")
       String senha,
       UsuarioRole role
) {


}
