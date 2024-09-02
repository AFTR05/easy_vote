package co.edu.cue.easy_vote.infrastructure.repository;

import co.edu.cue.easy_vote.domain.entities.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface VoteRepository extends JpaRepository<Vote,Long> {

    @Modifying
    @Query(value = "DELETE FROM vote", nativeQuery = true)
    void deleteAllVotes();


    @Modifying
    @Query(value = "DELETE FROM vote where candidate_id=?", nativeQuery = true)
    void deleteByCandidateId(Long candidateId);
}
