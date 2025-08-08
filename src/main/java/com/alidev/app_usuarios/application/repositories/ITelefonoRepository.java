package com.alidev.app_usuarios.application.repositories;


import com.alidev.app_usuarios.application.models.entities.Telefono;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ITelefonoRepository extends JpaRepository<Telefono, Long> {
}
