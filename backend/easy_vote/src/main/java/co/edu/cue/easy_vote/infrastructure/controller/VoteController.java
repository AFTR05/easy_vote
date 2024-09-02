package co.edu.cue.easy_vote.infrastructure.controller;

import co.edu.cue.easy_vote.domain.enums.StudyingDay;
import co.edu.cue.easy_vote.infrastructure.util.ResponseMessageUtil;
import co.edu.cue.easy_vote.mapping.dto.CandidateDTO;
import co.edu.cue.easy_vote.mapping.dto.CandidateRequestDTO;
import co.edu.cue.easy_vote.mapping.dto.VoteDTO;
import co.edu.cue.easy_vote.mapping.dto.VoteRequestDTO;
import co.edu.cue.easy_vote.service.CandidateService;
import co.edu.cue.easy_vote.service.VoteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/vote")
public class VoteController {
    private final VoteService service;

    @Operation(summary = "Mostrar los votos con estado verdadero"
            , description = "Este método se utilizará para traer todas los votos con estado verdadero")
    @GetMapping("/get-all")
    public ResponseEntity<Map<String, List<VoteDTO>>> getAllVotes(){
        List<VoteDTO> votes = service.listVotes();
        Map<String, List<VoteDTO>> response = ResponseMessageUtil.responseMessage("votes_data", votes);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Crear un voto"
            , description = "Este método se utilizará para crear un voto")
    @PostMapping("/create")
    public ResponseEntity<Map<String, VoteDTO>> createVote(@RequestBody
                                                                     @Valid
                                                                     VoteRequestDTO voteRequestDTO){
        VoteDTO createdVote = service.createVote(voteRequestDTO);
        Map<String, VoteDTO> response = ResponseMessageUtil.responseMessage("vote_data", createdVote);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Mostrar los votos con estado verdadero de un candidato en especifico"
            , description = "Este método se utilizará para traer todas los votos con estado verdadero de un candidato en especifico")
    @PostMapping("/filter-votes")
    public ResponseEntity<Map<String, List<VoteDTO>>> filterVotes(@RequestBody@Valid CandidateRequestDTO candidateRequestDTO){
        List<VoteDTO> votes= service.filterByCandidate(candidateRequestDTO.id());
        Map<String, List<VoteDTO>> response = ResponseMessageUtil.responseMessage("votes_data",votes);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Elimina todos los votos"
            , description = "Este método se utilizará para eliminar todos los votos")
    @DeleteMapping("/delete-votes")
    public ResponseEntity<Map<String, String>> deleteVotes(){
        service.deleteVotes();
        Map<String, String> response = ResponseMessageUtil.responseMessage("message", "Votes deleted");
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Cambia el estado del estudiante simulando que se elimino"
            , description = "Este método se utilizará para cambiar el estado del estudiante")
    @DeleteMapping("/delete-by-candidate")
    public ResponseEntity<Map<String, String>> deleteByCandidate(@RequestBody@Valid CandidateRequestDTO candidateRequestDTO){
        service.deleteVotesOfCandidate(candidateRequestDTO.id());
        Map<String, String> response = ResponseMessageUtil.responseMessage("message", "Votes of " +candidateRequestDTO.id() + " deleted");
        return ResponseEntity.ok(response);
    }

}
