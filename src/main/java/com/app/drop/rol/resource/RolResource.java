package com.app.drop.rol.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class RolResource {
    private Long id;
    private String nombre;
    private char estado;
}
