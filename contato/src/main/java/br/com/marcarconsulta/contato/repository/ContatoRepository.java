package br.com.marcarconsulta.contato.repository;

import br.com.marcarconsulta.contato.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

    public Optional<Contato> findByNome(String nome);

    @Query("SELECT c FROM Contato c WHERE c.nome =:nome")
    Optional<Contato> buscarContatoPeloNome(@Param("nome") String nome);

    @Query("SELECT c FROM Contato c WHERE c.dataNascimento BETWEEN :dataInicial AND :dataFinal")
    List<Contato> ListarAniversariantesDoPeriodo(
            @Param("dataInicial")LocalDate dataInicial,
              @Param("dataFinal")LocalDate dataFinal
    );


    Optional<Contato> findByEmail(String email);
}