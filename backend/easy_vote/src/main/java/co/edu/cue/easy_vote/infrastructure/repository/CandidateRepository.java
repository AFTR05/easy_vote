package co.edu.cue.easy_vote.infrastructure.repository;

import co.edu.cue.easy_vote.domain.entities.Candidate;
import co.edu.cue.easy_vote.domain.enums.StudyingDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate,Long> {
    List<Candidate> findByStudyingDay(StudyingDay studyingDay);

}
