package co.edu.cue.easy_vote.infrastructure.repository;

import co.edu.cue.easy_vote.domain.entities.Administrator;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministratorRepository extends JpaRepository<Administrator,Long> {
    Optional<Administrator> findById(@NotNull Long id);
    Administrator findAdministratorByUsername(String username);
}
