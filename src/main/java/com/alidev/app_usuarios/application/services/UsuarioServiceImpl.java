package com.alidev.app_usuarios.application.services;


import com.alidev.app_usuarios.application.components.UsuarioMapper;
import com.alidev.app_usuarios.application.exceptions.ServiceException;
import com.alidev.app_usuarios.application.models.dto.UsuarioRequestDTO;
import com.alidev.app_usuarios.application.models.dto.UsuarioResponseDTO;
import com.alidev.app_usuarios.application.models.entities.Usuario;
import com.alidev.app_usuarios.application.repositories.IUsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private final IUsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioServiceImpl(IUsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public UsuarioResponseDTO registrarUsuario(UsuarioRequestDTO usuarioRequestDTO) throws ServiceException {
        if (usuarioRequestDTO.getEmail() == null || usuarioRequestDTO.getEmail().isEmpty()) {
            throw new ServiceException("El correo no puede estar vacío");
        }

        validarFormatoEmail(usuarioRequestDTO.getEmail());

        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuarioRequestDTO.getEmail());
        if (usuarioExistente.isPresent()) {
            throw new ServiceException("El correo ya registrado");
        }

        Usuario usuario = usuarioMapper.toEntity(usuarioRequestDTO);

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return usuarioMapper.toResponseDTO(usuarioGuardado);
    }

    private void validarFormatoEmail(String email) throws ServiceException {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        if (!Pattern.matches(regex, email)) {
            throw new ServiceException("El formato del correo es inválido");
        }
    }
}
