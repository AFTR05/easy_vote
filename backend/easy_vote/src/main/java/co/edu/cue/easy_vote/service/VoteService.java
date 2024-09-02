package co.edu.cue.easy_vote.service;


import co.edu.cue.easy_vote.domain.entities.Candidate;
import co.edu.cue.easy_vote.domain.entities.Vote;
import co.edu.cue.easy_vote.domain.entities.Voter;
import co.edu.cue.easy_vote.mapping.dto.*;

import java.util.List;

public interface VoteService {
    VoteDTO createVote(VoteRequestDTO voteRequestDTO);
    void deleteVotes();

    void deleteVotesOfCandidate(Long candidate_id);

    List<VoteDTO> filterByCandidate(Long candidate_id);

    List<VoteDTO> listVotes();
}
