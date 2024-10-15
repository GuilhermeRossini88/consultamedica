package br.com.marcarconsulta.agendamento.service;

import br.com.marcarconsulta.agendamento.exception.UsuarioNaoEncontradoException;
import br.com.marcarconsulta.agendamento.model.Agendamento;
import br.com.marcarconsulta.agendamento.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {


    @Autowired
    private AgendamentoRepository agendamentoRepository;


    public void inserirAgendamentos() {
        LocalDate dataAtual = LocalDate.now();
        for (int i = 0; i < 30; i++) {
            LocalDate dataAgendamento = dataAtual.plusDays(i);
            for (int hora = 9; hora <= 18; hora++) {
                LocalDateTime dataHoraAgendamento = LocalDateTime.of(dataAgendamento, LocalTime.of(hora, 0));
                Agendamento agendamento = new Agendamento();
                agendamento.setNome("Agendamento às " + hora + "h");
                LocalDateTime data = dataHoraAgendamento;
                try {
                    Optional<Agendamento> agendamentoOptional = agendamentoRepository.findByData(data);
                    if (agendamentoOptional.isPresent()) {
                        throw new UsuarioNaoEncontradoException("A data e hora " + dataHoraAgendamento + " já está cadastrada");
                    } else {
                        agendamento.setData(dataHoraAgendamento);
                        agendamentoRepository.save(agendamento);
                    }
                } catch (UsuarioNaoEncontradoException e) {
                    System.out.println("Agenda já aberta.");
                }
            }
        }
    }
    public Agendamento atualizar(Agendamento agendamento) {
        Optional<Agendamento> agendamentoOptional = agendamentoRepository.findById(agendamento.getId());
        if (agendamentoOptional.isPresent()) {
            return agendamentoRepository.save(agendamento);
        } else {
            throw new RuntimeException("Data da Consulta não encontrada");
        }
    }
    public List<Agendamento> listarDatasDisponiveis() {
        return agendamentoRepository.datasDisponiveis();
    }

    public List<Agendamento> listarTodos() {
        return agendamentoRepository.findAll();
    }

}
