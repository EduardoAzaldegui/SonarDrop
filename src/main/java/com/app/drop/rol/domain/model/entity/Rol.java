package com.app.drop.rol.domain.model.entity;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ROL")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDROL")
    private Long id;

    @Column(name = "NOMROL")
    private String nombre;

    @Column(name = "ESTROL")
    private char estado;

}