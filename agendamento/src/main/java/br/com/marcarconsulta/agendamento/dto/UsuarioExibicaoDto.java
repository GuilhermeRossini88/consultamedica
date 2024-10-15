package br.com.marcarconsulta.agendamento.dto;

import br.com.marcarconsulta.agendamento.model.Usuario;
import br.com.marcarconsulta.agendamento.model.UsuarioRole;

public record UsuarioExibicaoDto(

        Long usuarioId,
        String nome,
        String email,
        UsuarioRole role
) {
    public UsuarioExibicaoDto(Usuario usuario){
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole());

    }


}
