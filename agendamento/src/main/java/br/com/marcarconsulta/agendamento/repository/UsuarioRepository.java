package br.com.marcarconsulta.agendamento.repository;

import br.com.marcarconsulta.agendamento.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByEmail(String email);

    public Optional<Usuario> findByNome(String nome);

    @Query("SELECT c FROM Usuario c WHERE c.nome =:nome")
    Optional<Usuario> buscarUsuarioPeloNome(@Param("nome") String nome);


//    Optional<Usuario> findByEmail(String email);

}
