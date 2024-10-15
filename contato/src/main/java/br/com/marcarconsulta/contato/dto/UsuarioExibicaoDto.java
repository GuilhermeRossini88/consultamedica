package br.com.marcarconsulta.contato.dto;

import br.com.marcarconsulta.contato.model.Usuario;
import br.com.marcarconsulta.contato.model.UsuarioRole;

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
