package br.com.marcarconsulta.contato.controller;

import br.com.marcarconsulta.contato.dto.LoginDto;
import br.com.marcarconsulta.contato.dto.TokenDto;
import br.com.marcarconsulta.contato.dto.UsuarioCadastroDto;
import br.com.marcarconsulta.contato.dto.UsuarioExibicaoDto;
import br.com.marcarconsulta.contato.model.Usuario;
import br.com.marcarconsulta.contato.security.TokenService;
import br.com.marcarconsulta.contato.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService service;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDto loginDto){

        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(
                        loginDto.email(),
                        loginDto.senha()
                );
        Authentication auth = authenticationManager.authenticate((usernamePassword));
        String token = tokenService.gerarToken((Usuario)auth.getPrincipal());

        return ResponseEntity.ok(new TokenDto(token));
    }


    @PostMapping("/registrar")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDto registrar(@RequestBody @Valid UsuarioCadastroDto usuarioCadastroDto){
        UsuarioExibicaoDto usuarioSalvo = null;
        usuarioSalvo = service.salvarUsuario(usuarioCadastroDto);
        return usuarioSalvo;
    }

}
