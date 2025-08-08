package com.alidev.app_usuarios.application.services;


import com.alidev.app_usuarios.application.components.UsuarioMapper;
import com.alidev.app_usuarios.application.exceptions.ServiceException;
import com.alidev.app_usuarios.application.models.dto.UsuarioRequestDTO;
import com.alidev.app_usuarios.application.models.dto.UsuarioResponseDTO;
import com.alidev.app_usuarios.application.models.entities.Usuario;
import com.alidev.app_usuarios.application.repositories.IUsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UsuarioServiceImplTest {
    @Mock
    private IUsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registrarUsuario_Exitoso() throws ServiceException {

        UsuarioRequestDTO requestDTO = UsuarioRequestDTO.builder()
                .name("Alinson Morales")
                .email("ali@gmail.com")
                .password("Lana_del_rey")
                .build();

        Usuario usuario = new Usuario();
        UsuarioResponseDTO responseDTO = UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .name("Alinson Morales")
                .email("ali@gmail.com")
                .build();

        when(usuarioRepository.findByEmail(requestDTO.getEmail())).thenReturn(Optional.empty());
        when(usuarioMapper.toEntity(requestDTO)).thenReturn(usuario);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        when(usuarioMapper.toResponseDTO(usuario)).thenReturn(responseDTO);

        UsuarioResponseDTO result = usuarioService.registrarUsuario(requestDTO);

        assertNotNull(result);
        assertEquals("Alinson Morales", result.getName());
        assertEquals("ali@gmail.com", result.getEmail());
        verify(usuarioRepository).findByEmail(requestDTO.getEmail());
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void registrarUsuario_EmailExistente() {

        UsuarioRequestDTO requestDTO = UsuarioRequestDTO.builder()
                .email("ali@gmail.com")
                .build();

        when(usuarioRepository.findByEmail(requestDTO.getEmail())).thenReturn(Optional.of(new Usuario()));

        ServiceException exception = assertThrows(ServiceException.class, () -> usuarioService.registrarUsuario(requestDTO));
        assertEquals("El correo ya registrado", exception.getMessage());
    }
}
