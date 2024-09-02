package co.edu.cue.easy_vote.infrastructure.controller;

import co.edu.cue.easy_vote.infrastructure.util.ResponseMessageUtil;
import co.edu.cue.easy_vote.mapping.dto.AdministratorAuthDTO;
import co.edu.cue.easy_vote.mapping.dto.AdministratorDTO;
import co.edu.cue.easy_vote.mapping.dto.AdministratorRequestDTO;
import co.edu.cue.easy_vote.service.AdministratorService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
public class AdministratorController {
    private final AdministratorService service;


    @Operation(summary = "Mostrar los administradores con estado verdadero"
            , description = "Este método se utilizará para traer todas los administradores con estado verdadero")
    @GetMapping("/get-all")
    public ResponseEntity<Map<String, List<AdministratorDTO>>> getAllAdmins(){
        List<AdministratorDTO> admins = service.getAdministrators();
        Map<String, List<AdministratorDTO>> response = ResponseMessageUtil.responseMessage("admins_data", admins);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Crear un administrador"
            , description = "Este método se utilizará para crear un administrador")
    @PostMapping("/create")
    public ResponseEntity<Map<String, AdministratorAuthDTO>> createAdmin(@RequestBody@Valid
                                                                          AdministratorRequestDTO admin){
        AdministratorAuthDTO createdAdmin = service.createAdmin(admin);
        Map<String, AdministratorAuthDTO> response = ResponseMessageUtil.responseMessage("admin_data", createdAdmin);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Mostrar solo un administrador con un id solicitado"
            , description = "Este método se utilizará para traer a un administrador con un id especifico")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Map<String, AdministratorDTO>> getAdminById(@PathVariable
                                                                Long id) {
        AdministratorDTO admin = service.getAdministratorById(id);
        Map<String, AdministratorDTO> response = ResponseMessageUtil.responseMessage("admin_data", admin);
        return ResponseEntity.ok(response);
    }
}
