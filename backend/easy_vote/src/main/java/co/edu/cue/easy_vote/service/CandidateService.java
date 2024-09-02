package co.edu.cue.easy_vote.service;
import co.edu.cue.easy_vote.domain.entities.Candidate;
import co.edu.cue.easy_vote.domain.enums.StudyingDay;
import co.edu.cue.easy_vote.mapping.dto.CandidateDTO;
import co.edu.cue.easy_vote.mapping.dto.CandidateRequestDTO;

import java.util.List;

public interface CandidateService {
    CandidateDTO createCandidate(CandidateRequestDTO candidateRequestDTO);

    List<CandidateDTO> getCandidates();

    CandidateDTO getCandidateById(Long id);

    CandidateDTO updateCandidate(CandidateRequestDTO candidate);

    void deactivatedCandidate(Long id);

    List<CandidateDTO> filterByStudyingDay(StudyingDay studyingDay);
}
