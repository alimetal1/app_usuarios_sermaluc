package com.alidev.app_usuarios.application.controller;

import com.alidev.app_usuarios.application.exceptions.ServiceException;
import com.alidev.app_usuarios.application.models.dto.UsuarioRequestDTO;
import com.alidev.app_usuarios.application.models.dto.UsuarioResponseDTO;
import com.alidev.app_usuarios.application.services.IUsuarioService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(
        value = "/api/usuarios",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
@Validated
public class UsuarioController {

    private final IUsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> registrarUsuario(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        try {
            UsuarioResponseDTO usuarioResponseDTO = usuarioService.registrarUsuario(usuarioRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioResponseDTO);
        } catch (ServiceException e) {
            ErrorResponse error = ErrorResponse.builder()
                    .mensaje(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @Data
    @Builder
    static class ErrorResponse {
        private String mensaje;
    }
}
