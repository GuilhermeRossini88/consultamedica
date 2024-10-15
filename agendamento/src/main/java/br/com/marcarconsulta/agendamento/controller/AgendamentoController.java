package br.com.marcarconsulta.agendamento.controller;

import br.com.marcarconsulta.agendamento.model.Agendamento;
import br.com.marcarconsulta.agendamento.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @PostMapping("/abrirAgenda")
    public String inserirAgendamentos() {
        agendamentoService.inserirAgendamentos();
        return "Agendamentos inseridos com sucesso.";
    }
    @PutMapping("/agendamento")
    @ResponseStatus(HttpStatus.OK)
    public Agendamento atualizar(@RequestBody Agendamento agendamento) {

        return agendamentoService.atualizar(agendamento);
    }

    @GetMapping("/agendamento")
    public ResponseEntity<List<Agendamento>> listarTodos() {
        List<Agendamento> agendamentos = agendamentoService.listarTodos();
        return ResponseEntity.ok(agendamentos);
    }
    @GetMapping("/disponivel")
    public ResponseEntity<List<Agendamento>> listarDatasDisponiveis() {
        List<Agendamento> agendamentos = agendamentoService.listarDatasDisponiveis();
        return ResponseEntity.ok(agendamentos);
    }
}
