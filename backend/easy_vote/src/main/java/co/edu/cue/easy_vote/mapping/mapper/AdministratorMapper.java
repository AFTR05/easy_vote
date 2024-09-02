package co.edu.cue.easy_vote.mapping.mapper;

import co.edu.cue.easy_vote.domain.entities.Administrator;
import co.edu.cue.easy_vote.mapping.dto.AdministratorDTO;
import co.edu.cue.easy_vote.mapping.dto.AdministratorRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface AdministratorMapper {
    @Mapping(target = "username", source = "source.username")
    AdministratorDTO mapFromEntity(Administrator source);

    @Mapping(target = "username", source = "source.username")
    Administrator mapFromDTO(AdministratorDTO source);

    @Mapping(target = "username", source = "source.username")
    Administrator mapFromRequestDTO(AdministratorRequestDTO source);

    List<AdministratorDTO> mapFromList(List<Administrator> source);
}
