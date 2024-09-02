package co.edu.cue.easy_vote.mapping.mapper;

import co.edu.cue.easy_vote.domain.entities.Administrator;
import co.edu.cue.easy_vote.domain.entities.Candidate;
import co.edu.cue.easy_vote.domain.entities.Vote;
import co.edu.cue.easy_vote.mapping.dto.*;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface VoteMapper {
    VoteDTO mapFromEntity(Vote source);

    Vote mapFromDTO(VoteDTO source);

    Vote mapFromRequestDTO(VoteRequestDTO source);

    List<VoteDTO> mapFromList(List<Vote> source);
}
