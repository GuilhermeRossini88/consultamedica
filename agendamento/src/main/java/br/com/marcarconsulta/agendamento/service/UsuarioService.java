package br.com.marcarconsulta.agendamento.service;

import br.com.marcarconsulta.agendamento.dto.UsuarioCadastroDto;
import br.com.marcarconsulta.agendamento.dto.UsuarioExibicaoDto;
import br.com.marcarconsulta.agendamento.exception.UsuarioNaoEncontradoException;
import br.com.marcarconsulta.agendamento.model.Usuario;
import br.com.marcarconsulta.agendamento.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioExibicaoDto salvarUsuario(UsuarioCadastroDto usuarioDTO){

        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioDTO.senha());
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuario);
        usuario.setSenha(senhaCriptografada);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioExibicaoDto(usuarioSalvo);
    }
    public UsuarioExibicaoDto buscarPorId(Long id) {

        Optional<Usuario> usuarioOptional= usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            return new UsuarioExibicaoDto(usuarioOptional.get());

        } else {
            throw new UsuarioNaoEncontradoException("Usuario não encontrado.");
        }
    }

    public Page<UsuarioExibicaoDto> listarTodosOsUsuarios(Pageable paginacao) {

        return usuarioRepository
                .findAll(paginacao)
                .map(UsuarioExibicaoDto::new);
    }

    public void excluir(Long id) {

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            usuarioRepository.delete(usuarioOptional.get());
        } else {
            throw new RuntimeException("Usuario não encontrado.");
        }
    }

    public Usuario atualizar(Usuario Usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(Usuario.getId());
        if (usuarioOptional.isPresent()) {
            return usuarioRepository.save(Usuario);
        } else {
            throw new RuntimeException("Usuario não encontrado.");
        }
    }

    public Usuario buscarPeloNome(String nome) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNome(nome);
        if (usuarioOptional.isPresent()) {
            return usuarioOptional.get();
        } else {
            throw new RuntimeException("Usuario não encontrado.");
        }
    }

    public UsuarioExibicaoDto buscarUsuarioPeloNome(String nome) {
        Optional<Usuario> usuarioOptional = usuarioRepository.buscarUsuarioPeloNome(nome);
        if (usuarioOptional.isPresent()) {
            return new UsuarioExibicaoDto(usuarioOptional.get());
        } else {
            throw new UsuarioNaoEncontradoException("Usuario não encontrado.");
        }
    }


//    public UsuarioExibicaoDto buscarUsuarioPeloEmail(String email){
//        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
//
//        if(usuarioOptional.isPresent()){
//            return new UsuarioExibicaoDto((usuarioOptional.get()));
//        }else{
//            throw new UsuarioNaoEncontradoException("Usuario não encontrado.");
//        }
//    }


}
