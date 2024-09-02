package co.edu.cue.easy_vote.service.impl;

import co.edu.cue.easy_vote.domain.entities.Candidate;
import co.edu.cue.easy_vote.domain.enums.StudyingDay;
import co.edu.cue.easy_vote.infrastructure.exception.AdministratorException;
import co.edu.cue.easy_vote.infrastructure.exception.CandidateException;
import co.edu.cue.easy_vote.infrastructure.repository.AdministratorRepository;
import co.edu.cue.easy_vote.infrastructure.repository.CandidateRepository;
import co.edu.cue.easy_vote.mapping.dto.CandidateDTO;
import co.edu.cue.easy_vote.mapping.dto.CandidateRequestDTO;
import co.edu.cue.easy_vote.mapping.mapper.AdministratorMapper;
import co.edu.cue.easy_vote.mapping.mapper.CandidateMapper;
import co.edu.cue.easy_vote.service.CandidateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final CandidateMapper mapper;
    @Override
    public CandidateDTO createCandidate(CandidateRequestDTO candidateRequestDTO) {
        if (candidateRepository.findAll().stream().anyMatch(stu -> stu.getId().equals(candidateRequestDTO.id()))) throw new CandidateException("Candidato Repetido");
        Candidate dataModification = mapper.mapFromRequestDTO(candidateRequestDTO);
        try {
            dataModification.setData_state(true);
            Candidate savedComplaint = candidateRepository.save(dataModification);
            return mapper.mapFromEntity(savedComplaint);
        } catch (Exception e) {
            throw new CandidateException("Error while saving the candidate");
        }
    }

    @Override
    public List<CandidateDTO> getCandidates() {
        return candidateRepository.findAll()
                .stream()
                .map(mapper::mapFromEntity).toList();
    }

    @Override
    public CandidateDTO getCandidateById(Long id) {
        return mapper.mapFromEntity(candidateRepository.getReferenceById(id));
    }

    @Override
    public CandidateDTO updateCandidate(CandidateRequestDTO candidate) {
        Candidate dataModified = mapper.mapFromRequestDTO(candidate);
        if (candidateRepository.findAll().stream().anyMatch(can -> can.getId().equals(candidate.id()))) {
            dataModified.setData_state(true);
            return mapper.mapFromEntity(candidateRepository.save(dataModified));
        } else throw new CandidateException("Candidato Repetido");

    }

    @Override
    public void deactivatedCandidate(Long id) {
        Candidate student=candidateRepository.getReferenceById(id);
        student.setData_state(false);
        candidateRepository.save(student);

    }

    @Override
    public List<CandidateDTO> filterByStudyingDay(StudyingDay studyingDay) {
        return candidateRepository.findByStudyingDay(studyingDay).stream().map(mapper::mapFromEntity).toList();
    }
}
