package co.edu.cue.easy_vote.infrastructure.repository;

import co.edu.cue.easy_vote.domain.entities.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterRepository extends JpaRepository<Voter,Long> {
    Voter findVoterByNid(String nid);


}
