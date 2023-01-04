package teste.residencia.neki.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import teste.residencia.neki.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);
    
}
