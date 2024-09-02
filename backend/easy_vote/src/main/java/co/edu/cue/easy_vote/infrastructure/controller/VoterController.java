package co.edu.cue.easy_vote.infrastructure.controller;

import co.edu.cue.easy_vote.domain.entities.Voter;
import co.edu.cue.easy_vote.infrastructure.util.ResponseMessageUtil;
import co.edu.cue.easy_vote.mapping.dto.*;
import co.edu.cue.easy_vote.service.AdministratorService;
import co.edu.cue.easy_vote.service.VoterService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/voter")
public class VoterController {

    private final VoterService service;

    @Operation(summary = "Mostrar los votantes con estado verdadero"
            , description = "Este método se utilizará para traer todas los votantes con estado verdadero")
    @GetMapping("/get-all")
    public ResponseEntity<Map<String, List<VoterDTO>>> getAllVoters(){
        List<VoterDTO> voters = service.getVoters();
        Map<String, List<VoterDTO>> response = ResponseMessageUtil.responseMessage("voters_data", voters);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Crear un administrador"
            , description = "Este método se utilizará para crear un administrador")
    @PostMapping("/create")
    public ResponseEntity<Map<String, VoterDTO>> createVoter(@RequestBody
                                                                         @Valid
                                                             VoterRequestDTO voter){
        VoterDTO createdVoter = service.createVoter(voter);
        Map<String, VoterDTO> response = ResponseMessageUtil.responseMessage("voter_data", createdVoter);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Mostrar solo un administrador con un id solicitado"
            , description = "Este método se utilizará para traer a un administrador con un id especifico")
    @GetMapping("/get-by-nid/{nid}")
    public ResponseEntity<Map<String, VoterDTO>> getVoterByNid(@PathVariable
                                                                      String nid) {
        VoterDTO voter = service.getVoter(nid);
        Map<String, VoterDTO> response = ResponseMessageUtil.responseMessage("voter_data", voter);
        return ResponseEntity.ok(response);
    }
}
