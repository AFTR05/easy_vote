package co.edu.cue.easy_vote.infrastructure.controller;

import co.edu.cue.easy_vote.domain.enums.StudyingDay;
import co.edu.cue.easy_vote.infrastructure.util.ResponseMessageUtil;
import co.edu.cue.easy_vote.mapping.dto.*;
import co.edu.cue.easy_vote.service.AdministratorService;
import co.edu.cue.easy_vote.service.CandidateService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/candidate")
public class CandidateController {
    private final CandidateService service;

    @Operation(summary = "Mostrar los candidatos con estado verdadero"
            , description = "Este método se utilizará para traer todas los candidatos con estado verdadero")
    @GetMapping("/get-all")
    public ResponseEntity<Map<String, List<CandidateDTO>>> getAllCandidates(){
        List<CandidateDTO> candidates = service.getCandidates();
        Map<String, List<CandidateDTO>> response = ResponseMessageUtil.responseMessage("candidates_data", candidates);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Crear un candidato"
            , description = "Este método se utilizará para crear un candidato")
    @PostMapping("/create")
    public ResponseEntity<Map<String, CandidateDTO>> createCandidate(@RequestBody
                                                                         @Valid
                                                                     CandidateRequestDTO candidateRequestDTO){
        CandidateDTO createdCandidate = service.createCandidate(candidateRequestDTO);
        Map<String, CandidateDTO> response = ResponseMessageUtil.responseMessage("candidate_data", createdCandidate);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Mostrar solo un candidato con un id solicitado"
            , description = "Este método se utilizará para traer a un candidato con un id especifico")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Map<String, CandidateDTO>> getCandidateById(@PathVariable
                                                                      Long id) {
        CandidateDTO candidate = service.getCandidateById(id);
        Map<String, CandidateDTO> response = ResponseMessageUtil.responseMessage("candidate_data", candidate);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Actualizar un candidato"
            , description = "Este método se utilizará para actualizar un candidato")
    @PatchMapping("/update")
    public ResponseEntity<Map<String, CandidateDTO>> updateCandidate(@RequestBody
                                                               @Valid
                                                               CandidateRequestDTO candidateRequestDTO){
        CandidateDTO candidate = service.updateCandidate(candidateRequestDTO);
        Map<String, CandidateDTO> response = ResponseMessageUtil.responseMessage("candidate_data", candidate);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Cambia el estado del candidato simulando que se elimino"
            , description = "Este método se utilizará para cambiar el estado del candidato")
    @PatchMapping("/delete-candidate/{id}")
    public ResponseEntity<Map<String, String>> deleteCandidate(@PathVariable Long id){
        service.deactivatedCandidate(id);
        Map<String, String> response = ResponseMessageUtil.responseMessage("message", "Candidate deleted");
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Mostrar los candidatos con estado verdadero con una jornada en especifico"
            , description = "Este método se utilizará para traer todas los candidatos con estado verdadero con una jornada en especifico")
    @GetMapping("/filter-candidates/{studyingDay}")
    public ResponseEntity<Map<String, List<CandidateDTO>>> filterCandidates(@PathVariable StudyingDay studyingDay){
        List<CandidateDTO> candidates= service.filterByStudyingDay(studyingDay);
        Map<String, List<CandidateDTO>> response = ResponseMessageUtil.responseMessage("candidates_data",candidates);
        return ResponseEntity.ok(response);
    }


}
