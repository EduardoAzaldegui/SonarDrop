package com.app.drop.negocio.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateNegocioResource {
    private String tipo;
    private String nombre;
    private String paginaWeb;
    private String contacto;
    private String numeroContacto;
    private String logo;
    private String email;
    private String ruc;

}