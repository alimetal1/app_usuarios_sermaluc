package com.alidev.app_usuarios.application.components;

import com.alidev.app_usuarios.application.models.dto.TelefonoDTO;
import com.alidev.app_usuarios.application.models.dto.UsuarioRequestDTO;
import com.alidev.app_usuarios.application.models.dto.UsuarioResponseDTO;
import com.alidev.app_usuarios.application.models.entities.Telefono;
import com.alidev.app_usuarios.application.models.entities.Usuario;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UsuarioMapperTest {

    private final UsuarioMapper usuarioMapper = new UsuarioMapper();

    @Test
    void toEntity() {
        UsuarioRequestDTO requestDTO = UsuarioRequestDTO.builder()
                .name("Alinson Morales")
                .email("ali@gmail.com")
                .password("Lana_del_rey")
                .phones(Collections.singletonList(
                        TelefonoDTO.builder()
                                .number("1234567")
                                .citycode("1")
                                .contrycode("57")
                                .build()
                ))
                .build();

        Usuario usuario = usuarioMapper.toEntity(requestDTO);

        assertNotNull(usuario);
        assertEquals("Alinson Morales", usuario.getName());
        assertEquals("ali@gmail.com", usuario.getEmail());
        assertNotNull(usuario.getPhones());
        assertEquals(1, usuario.getPhones().size());
        assertEquals("1234567", usuario.getPhones().get(0).getNumber());
    }

    @Test
    void toResponseDTO() {

        Usuario usuario = new Usuario();
        usuario.setId(UUID.randomUUID());
        usuario.setName("Alinson Morales");
        usuario.setEmail("ali@gmail.com");
        usuario.setCreated(LocalDateTime.now());
        usuario.setPhones(Collections.singletonList(
                new Telefono()
        ));
        UsuarioResponseDTO responseDTO = usuarioMapper.toResponseDTO(usuario);

        assertNotNull(responseDTO);
        assertEquals("Alinson Morales", responseDTO.getName());
        assertEquals("ali@gmail.com", responseDTO.getEmail());
        assertNotNull(responseDTO.getPhones());
        assertEquals(1, responseDTO.getPhones().size());
    }
}
