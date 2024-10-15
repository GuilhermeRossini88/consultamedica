package br.com.marcarconsulta.contato.dto;

import br.com.marcarconsulta.contato.model.Contato;
import jakarta.persistence.Column;

import java.time.LocalDate;

public record ContatoExibicaoDto(

        Long id,
        String nome,
        @Column(name = "data_nascimento")
        LocalDate dataNascimento,
        String email
) {

    public ContatoExibicaoDto(Contato contato){
        this(
                contato.getId(),
                contato.getNome(),
                contato.getDataNascimento(),
                contato.getEmail()
        );
    }

}
