package br.com.marcarconsulta.contato.controller;

import br.com.marcarconsulta.contato.dto.ContatoCadastroDto;
import br.com.marcarconsulta.contato.dto.ContatoExibicaoDto;
import br.com.marcarconsulta.contato.model.Contato;
import br.com.marcarconsulta.contato.service.ContatoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ContatoController {
    @Autowired
    private ContatoService service;

    @PostMapping("/contatos")
    @ResponseStatus(HttpStatus.CREATED)
    public ContatoExibicaoDto gravar(@RequestBody @Valid ContatoCadastroDto contatoCadastroDto) {

        return service.gravar(contatoCadastroDto);
    }

    @GetMapping("/buscarPeloId/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContatoExibicaoDto buscarPorId(@PathVariable Long id) {

        return service.buscarPorId(id);
    }

    @GetMapping("/contatos")
    @ResponseStatus(HttpStatus.OK)
    public Page<ContatoExibicaoDto> buscarTodosOsContatos(Pageable paginacao) {

        return service.listarTodosOsContatos(paginacao);
    }

    @DeleteMapping("/contatos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {

        service.excluir(id);
    }

    @PutMapping("/contatos")
    @ResponseStatus(HttpStatus.OK)
    public Contato atualizar(@RequestBody Contato contato) {

        return service.atualizar(contato);
    }

    @GetMapping("/buscarPeloNome/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public Contato buscarPeloNome(@PathVariable String nome) {

        return service.buscarPeloNome(nome);
    }

    @GetMapping("/contatosDTO/{nome}")
    public ContatoExibicaoDto buscarContatoPeloNome(@PathVariable String nome) {
        return service.buscarContatoPeloNome(nome);
    }

    @GetMapping(value = "/contatos", params = "nome")
    public ContatoExibicaoDto buscarContatoPorNome(@RequestParam String nome) {
        return service.buscarContatoPeloNome(nome);
    }

    @GetMapping(value = "/contatos",
            params = {"dataInicial", "dataFinal"})
    public List<ContatoExibicaoDto> ListarAniversariantesDoPeriodo(
            @RequestParam LocalDate dataInicial,
            @RequestParam LocalDate dataFinal

    ) {
        return service.ListarAniversariantesDoPeriodo(dataInicial, dataFinal);
    }

    @GetMapping("/buscarPorEmail/{email}")
    public ContatoExibicaoDto buscarContatoPeloEmail(@PathVariable String email){
        return service.buscarContatoPeloEmail(email);
    }

}
