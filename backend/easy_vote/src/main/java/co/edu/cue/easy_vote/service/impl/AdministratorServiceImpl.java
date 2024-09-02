package co.edu.cue.easy_vote.service.impl;

import co.edu.cue.easy_vote.domain.entities.Administrator;
import co.edu.cue.easy_vote.domain.entities.Token;
import co.edu.cue.easy_vote.domain.enums.CodeMessage;
import co.edu.cue.easy_vote.infrastructure.exception.AdministratorException;
import co.edu.cue.easy_vote.infrastructure.repository.AdministratorRepository;
import co.edu.cue.easy_vote.infrastructure.repository.TokenRepository;
import co.edu.cue.easy_vote.infrastructure.util.TokenGenerator;
import co.edu.cue.easy_vote.infrastructure.util.Validation;
import co.edu.cue.easy_vote.mapping.dto.*;
import co.edu.cue.easy_vote.mapping.mapper.AdministratorMapper;
import co.edu.cue.easy_vote.security.JwtService;
import co.edu.cue.easy_vote.service.AdministratorService;
import co.edu.cue.easy_vote.service.LoginService;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdministratorServiceImpl implements AdministratorService, LoginService<AdministratorAuthDTO, AdministratorRequestDTO> {

    private final AdministratorRepository administratorRepository;
    private final AdministratorMapper mapper;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;



    @Override
    public AdministratorAuthDTO login(AdministratorRequestDTO s) {
        AdministratorAuthDTO administratorAuthDTO = new AdministratorAuthDTO();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(s.username(),s.password()));
        Administrator user =administratorRepository.findAdministratorByUsername(s.username());
        if (!Validation.isNull(user)){
            return getAdministratorAuthDTO(administratorAuthDTO, user);
        }
        administratorAuthDTO.setStatusDTO(new StatusDTO(CodeMessage.ERROR_INVALID_RESULT.getCode(),CodeMessage.ERROR_INVALID_RESULT.getMessage()));
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario no encontrado");
    }

    @NotNull
    private AdministratorAuthDTO getAdministratorAuthDTO(AdministratorAuthDTO administratorAuthDTO, Administrator user) {
        String jwt = jwtService.generateToken(user);
        Token token = TokenGenerator.generateToken(jwt, user);
        List<Token> validTokens = tokenRepository.findByAdministratorAndIsLogOut(user, false);
        if (!validTokens.isEmpty()) {
            validTokens.forEach(x -> {
                x.setIsLogOut(true);
            });
        }
        tokenRepository.saveAll(validTokens);
        tokenRepository.save(token);
        administratorAuthDTO.setAuthenticationResponseDTO(new AuthenticationResponseDTO(jwt));
        administratorAuthDTO.setAdministratorDTO(mapper.mapFromEntity(user));
        administratorAuthDTO.setStatusDTO(new StatusDTO(CodeMessage.SUCCESSFUL.getCode(),CodeMessage.SUCCESSFUL.getMessage()));
        return administratorAuthDTO;
    }

    @Override
    public AdministratorAuthDTO createAdmin(AdministratorRequestDTO administratorRequestDTO) {
        Administrator dataModification=mapper.mapFromRequestDTO(administratorRequestDTO);
        if (administratorRepository.findAll().stream().anyMatch(stu -> stu.getUsername().equals(administratorRequestDTO.username()))) throw new AdministratorException("Administrador Repetido");
        dataModification.setPassword(passwordEncoder.encode(administratorRequestDTO.password()));
        dataModification.setData_state(true);
        AdministratorAuthDTO administratorAuthDTO=new AdministratorAuthDTO();
        AdministratorDTO administratorDTO=mapper.mapFromEntity(dataModification);
        Administrator newAdmin = administratorRepository.save(mapper.mapFromDTO(administratorDTO));
        if (!Validation.isNull(newAdmin)){
            return getAdministratorAuthDTO(administratorAuthDTO, newAdmin);
        } else {
            administratorAuthDTO.setStatusDTO(new StatusDTO(CodeMessage.ERROR_INVALID_RESULT.getCode(), CodeMessage.ERROR_INVALID_RESULT.getMessage()));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "administrativo no encontrado");
        }
    }

    @Override
    public List<AdministratorDTO> getAdministrators() {
        return administratorRepository.findAll()
                .stream()
                .map(mapper::mapFromEntity).toList();
    }

    @Override
    public AdministratorDTO getAdministratorById(Long id) {
        return administratorRepository.findById(id)
                .map(administrator -> {
                    try {
                        return mapper.mapFromEntity(administrator);
                    } catch (Exception e) {
                        throw new AdministratorException("Error al obtener el administrativo");
                    }
                })
                .orElseThrow(() -> new AdministratorException("Administrativo no encontrado"));
    }
}
