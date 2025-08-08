package com.alidev.app_usuarios.application.components;


import com.alidev.app_usuarios.application.models.dto.TelefonoDTO;
import com.alidev.app_usuarios.application.models.dto.UsuarioRequestDTO;
import com.alidev.app_usuarios.application.models.dto.UsuarioResponseDTO;
import com.alidev.app_usuarios.application.models.entities.Telefono;
import com.alidev.app_usuarios.application.models.entities.Usuario;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UsuarioMapper {
    public Usuario toEntity(UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = new Usuario();
        usuario.setId(UUID.randomUUID());
        usuario.setName(usuarioRequestDTO.getName());
        usuario.setEmail(usuarioRequestDTO.getEmail());
        usuario.setPassword(usuarioRequestDTO.getPassword());
        usuario.setCreated(LocalDateTime.now());
        usuario.setModified(LocalDateTime.now());
        usuario.setLastLogin(LocalDateTime.now());
        usuario.setActive(true);
        usuario.setToken(UUID.randomUUID().toString());

        // Mapear los teléfonos
        List<Telefono> telefonos = usuarioRequestDTO.getPhones().stream().map(phoneDTO -> {
            Telefono telefono = new Telefono();
            telefono.setNumber(phoneDTO.getNumber());
            telefono.setCitycode(phoneDTO.getCitycode());
            telefono.setContrycode(phoneDTO.getContrycode());
            telefono.setUsuario(usuario); // Establecer la relación
            return telefono;
        }).collect(Collectors.toList());
        usuario.setPhones(telefonos);

        return usuario;
    }

    public UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        return UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .name(usuario.getName())
                .email(usuario.getEmail())
                .created(usuario.getCreated())
                .modified(usuario.getModified())
                .lastLogin(usuario.getLastLogin())
                .isActive(usuario.isActive())
                .token(usuario.getToken())
                .phones(usuario.getPhones() == null ?
                        Collections.emptyList() :
                        usuario.getPhones().stream().map(phone -> TelefonoDTO.builder()
                                .number(phone.getNumber())
                                .citycode(phone.getCitycode())
                                .contrycode(phone.getContrycode())
                                .build()).collect(Collectors.toList()))
                .build();
    }
}
