package com.alidev.app_usuarios.application.controller;


import com.alidev.app_usuarios.application.exceptions.ServiceException;
import com.alidev.app_usuarios.application.models.dto.UsuarioRequestDTO;
import com.alidev.app_usuarios.application.models.dto.UsuarioResponseDTO;
import com.alidev.app_usuarios.application.services.IUsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class UsuarioControllerTest {
    @Mock
    private IUsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registrarUsuario_Exitoso() throws Exception {

        UsuarioRequestDTO requestDTO = UsuarioRequestDTO.builder()
                .name("Alinson Morales")
                .email("ali@gmail.com")
                .password("Lana_del_rey")
                .build();

        UsuarioResponseDTO responseDTO = UsuarioResponseDTO.builder()
                .id(null)
                .name("Alinson Morales")
                .email("ali@gmail.com")
                .build();

        when(usuarioService.registrarUsuario(requestDTO)).thenReturn(responseDTO);

        ResponseEntity<?> response = usuarioController.registrarUsuario(requestDTO);

        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void registrarUsuario_ErrorService() throws Exception {
        UsuarioRequestDTO requestDTO = UsuarioRequestDTO.builder()
                .name("Alinson Morales")
                .email("ali@gmail.com")
                .password("Lana_del_rey")
                .build();

        when(usuarioService.registrarUsuario(requestDTO))
                .thenThrow(new ServiceException("El correo ya registrado"));

        ResponseEntity<?> response = usuarioController.registrarUsuario(requestDTO);

        assertNotNull(response);
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof UsuarioController.ErrorResponse);

        UsuarioController.ErrorResponse errorResponse = (UsuarioController.ErrorResponse) response.getBody();
        assertEquals("El correo ya registrado", errorResponse.getMensaje());
    }
}
