package co.edu.cue.easy_vote.service.impl;

import co.edu.cue.easy_vote.domain.entities.Candidate;
import co.edu.cue.easy_vote.domain.entities.Vote;
import co.edu.cue.easy_vote.domain.entities.Voter;
import co.edu.cue.easy_vote.infrastructure.exception.CandidateException;
import co.edu.cue.easy_vote.infrastructure.exception.VoteException;
import co.edu.cue.easy_vote.infrastructure.exception.VoterException;
import co.edu.cue.easy_vote.infrastructure.repository.CandidateRepository;
import co.edu.cue.easy_vote.infrastructure.repository.VoteRepository;
import co.edu.cue.easy_vote.infrastructure.repository.VoterRepository;
import co.edu.cue.easy_vote.mapping.dto.*;
import co.edu.cue.easy_vote.mapping.mapper.VoteMapper;
import co.edu.cue.easy_vote.mapping.mapper.VoterMapper;
import co.edu.cue.easy_vote.service.VoteService;
import co.edu.cue.easy_vote.service.VoterService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VoteServiceImpl implements VoteService {
    private final VoteRepository voteRepository;
    private final VoterRepository voterRepository;
    private final CandidateRepository candidateRepository;
    private final VoteMapper mapper;
    private final VoterMapper voterMapper;
    @Override
    public VoteDTO createVote(VoteRequestDTO voteRequestDTO) {
        Candidate candidate = candidateRepository.findById(voteRequestDTO.candidate_id())
                .orElseThrow(() -> new CandidateException("candidate not found"));
        Voter voter = Optional.ofNullable(voterRepository.findVoterByNid(voteRequestDTO.voter_nid()))
                .orElseThrow(() -> new VoterException("voter not found"));
        Vote dataModification = mapper.mapFromRequestDTO(voteRequestDTO);
        dataModification.setCandidate(candidate);
        dataModification.setVoter(voter);
        try {
            Vote savedVote = voteRepository.save(dataModification);
            return mapper.mapFromEntity(savedVote);
        } catch (Exception e) {
            throw new VoteException("Error while saving the vote");
        }
    }

    @Override
    @Transactional
    public void deleteVotes() {
        voteRepository.deleteAllVotes();
    }

    @Override
    @Transactional
    public void deleteVotesOfCandidate(Long candidate_id) {
        voteRepository.deleteByCandidateId(candidate_id);
    }

    @Override
    public List<VoteDTO> filterByCandidate(Long candidate_id) {
        return voteRepository.findAll()
                .stream()
                .filter(vote -> vote.getCandidate().getId().equals(candidate_id))
                .map(mapper::mapFromEntity).toList();
    }

    @Override
    public List<VoteDTO> listVotes() {
        return voteRepository.findAll()
                .stream()
                .map(mapper::mapFromEntity).toList();
    }
}
