package co.edu.cue.easy_vote.mapping.mapper;

import co.edu.cue.easy_vote.domain.entities.Administrator;
import co.edu.cue.easy_vote.domain.entities.Voter;
import co.edu.cue.easy_vote.mapping.dto.*;
import org.springframework.stereotype.Component;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface VoterMapper {
    VoterDTO mapFromEntity(Voter source);

    Voter mapFromDTO(VoterDTO source);

    Voter mapFromRequestDTO(VoterRequestDTO source);

    List<VoterDTO> mapFromList(List<Voter> source);
}
