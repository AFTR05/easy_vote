package co.edu.cue.easy_vote.infrastructure.controller;

import co.edu.cue.easy_vote.infrastructure.util.ResponseMessageUtil;
import co.edu.cue.easy_vote.mapping.dto.AdministratorAuthDTO;
import co.edu.cue.easy_vote.mapping.dto.AdministratorRequestDTO;
import co.edu.cue.easy_vote.mapping.dto.VoterRequestDTO;
import co.edu.cue.easy_vote.service.AdministratorService;
import co.edu.cue.easy_vote.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/login")
public class LoginController {
    private final LoginService<AdministratorAuthDTO, AdministratorRequestDTO> adminService;
    private final LoginService<Boolean, VoterRequestDTO> voterService;

    @Operation(summary = "Loggea a un administrador"
            , description = "Este método se utilizará para loggear a un administrador")
    @PostMapping("/admin")
    public ResponseEntity<Map<String, AdministratorAuthDTO>> adminLogin(@RequestBody @Valid
                                                                         AdministratorRequestDTO admin){
        AdministratorAuthDTO loggedAdmin = adminService.login(admin);
        Map<String, AdministratorAuthDTO> response = ResponseMessageUtil.responseMessage("admin_data", loggedAdmin);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Loggea a un votante"
            , description = "Este método se utilizará para loggear a un votante")
    @PostMapping("/voter")
    public ResponseEntity<Map<String, Boolean>> voterLogin(@RequestBody @Valid
                                                           VoterRequestDTO voter){
        Boolean isLogged = voterService.login(voter);
        Map<String, Boolean> response = ResponseMessageUtil.responseMessage("message", isLogged);
        return ResponseEntity.ok(response);
    }
}
