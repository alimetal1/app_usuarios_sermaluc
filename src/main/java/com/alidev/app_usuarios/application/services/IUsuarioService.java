package com.alidev.app_usuarios.application.services;


import com.alidev.app_usuarios.application.exceptions.ServiceException;
import com.alidev.app_usuarios.application.models.dto.UsuarioRequestDTO;
import com.alidev.app_usuarios.application.models.dto.UsuarioResponseDTO;

public interface IUsuarioService {
    UsuarioResponseDTO registrarUsuario (UsuarioRequestDTO usuarioRequestDTO) throws ServiceException;
}
