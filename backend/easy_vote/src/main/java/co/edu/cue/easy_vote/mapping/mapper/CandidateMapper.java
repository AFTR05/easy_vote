package co.edu.cue.easy_vote.mapping.mapper;

import co.edu.cue.easy_vote.domain.entities.Administrator;
import co.edu.cue.easy_vote.domain.entities.Candidate;
import co.edu.cue.easy_vote.mapping.dto.AdministratorDTO;
import co.edu.cue.easy_vote.mapping.dto.AdministratorRequestDTO;
import co.edu.cue.easy_vote.mapping.dto.CandidateDTO;
import co.edu.cue.easy_vote.mapping.dto.CandidateRequestDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface CandidateMapper {
    CandidateDTO mapFromEntity(Candidate source);

    Candidate mapFromDTO(CandidateDTO source);

    Candidate mapFromRequestDTO(CandidateRequestDTO source);

    List<CandidateDTO> mapFromList(List<Candidate> source);
}
