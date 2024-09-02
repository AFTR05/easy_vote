package co.edu.cue.easy_vote.infrastructure.repository;

import co.edu.cue.easy_vote.domain.entities.Administrator;
import co.edu.cue.easy_vote.domain.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Integer> {
    List<Token> findByAdministratorAndIsLogOut(Administrator administrator, Boolean isLogged);
    Optional<Token> findByToken(String token);
}
