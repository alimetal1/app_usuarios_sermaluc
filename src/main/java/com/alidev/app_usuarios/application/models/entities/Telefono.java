package com.alidev.app_usuarios.application.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;

@Data
@Entity
public class Telefono {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "El número no puede estar vacío")
    private String number;

    @NotEmpty(message = "El código de ciudad no puede estar vacío")
    private String citycode;

    @NotEmpty(message = "El código de país no puede estar vacío")
    private String contrycode;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
