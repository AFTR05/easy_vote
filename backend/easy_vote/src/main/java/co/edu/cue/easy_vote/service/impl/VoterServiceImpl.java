package co.edu.cue.easy_vote.service.impl;

import co.edu.cue.easy_vote.domain.entities.Candidate;
import co.edu.cue.easy_vote.domain.entities.Voter;
import co.edu.cue.easy_vote.infrastructure.exception.CandidateException;
import co.edu.cue.easy_vote.infrastructure.exception.VoterException;
import co.edu.cue.easy_vote.infrastructure.repository.VoterRepository;
import co.edu.cue.easy_vote.mapping.dto.VoterDTO;
import co.edu.cue.easy_vote.mapping.dto.VoterRequestDTO;
import co.edu.cue.easy_vote.mapping.mapper.VoterMapper;
import co.edu.cue.easy_vote.service.LoginService;
import co.edu.cue.easy_vote.service.VoteService;
import co.edu.cue.easy_vote.service.VoterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VoterServiceImpl implements VoterService, LoginService<Boolean, VoterRequestDTO> {
    private final VoterRepository voterRepository;
    private final VoteService voteService;
    private final VoterMapper mapper;

    @Override
    public Boolean login(VoterRequestDTO voterRequestDTO) {
        String voterNid = voterRequestDTO.nid();
        Optional<VoterDTO> bdVoterOpt = getVoterByNID(voterNid);
        if (bdVoterOpt.isEmpty()) {
            createVoter(voterRequestDTO);
            return true;
        }
        VoterDTO bdVoter = bdVoterOpt.get();
        boolean hasVoted = voteService.listVotes().stream()
                .anyMatch(x -> x.voter().getNid().equals(voterNid));
        return bdVoter.studyingDay().equals(voterRequestDTO.studyingDay()) && !hasVoted;
    }

    @Override
    public VoterDTO createVoter(VoterRequestDTO voterRequestDTO) {
        if (voterRepository.findAll().stream().anyMatch(stu -> stu.getNid().equals(voterRequestDTO.nid()))) throw new VoterException("Votante Repetido");
        Voter dataModification = mapper.mapFromRequestDTO(voterRequestDTO);
        try {
            dataModification.setData_state(true);
            Voter savedComplaint = voterRepository.save(dataModification);
            return mapper.mapFromEntity(savedComplaint);
        } catch (Exception e) {
            throw new VoterException("Error while saving the voter");
        }
    }

    @Override
    public List<VoterDTO> getVoters() {
        return voterRepository.findAll()
                .stream()
                .map(mapper::mapFromEntity).toList();
    }

    @Override
    public VoterDTO getVoter(String nid) {
        return mapper.mapFromEntity(voterRepository.findVoterByNid(nid));
    }

    @Override
    public Optional<VoterDTO> getVoterByNID(String nid) {
        return getVoters().stream().filter(x -> x.nid().equals(nid)).findFirst();
    }
}
