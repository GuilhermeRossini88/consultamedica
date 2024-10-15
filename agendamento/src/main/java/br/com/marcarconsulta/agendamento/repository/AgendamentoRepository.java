package br.com.marcarconsulta.agendamento.repository;

import br.com.marcarconsulta.agendamento.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    @Query("SELECT a FROM Agendamento a WHERE a.agendado = FALSE OR a.agendado IS NULL")
    List<Agendamento> datasDisponiveis();

    Optional<Agendamento> findByData(LocalDateTime data);
}
