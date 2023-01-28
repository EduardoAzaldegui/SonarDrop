package com.app.drop.negocio.resource;

import lombok.*;

import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class NegocioResource {
    private Long id;
    private String tipo;
    private String nombre;
    private String paginaWeb;
    private String contacto;
    private String numeroContacto;
    private String logo;
    private String email;
    private String ruc;
    private List<Long> productos;
    private List<Long> empleados;
    private List<Long> listaDeseos;
}