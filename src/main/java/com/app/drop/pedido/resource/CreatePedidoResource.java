package com.app.drop.pedido.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreatePedidoResource {
    private String nombre;
    private String direccion;
    private String telefono;
    private String referencia;
    private String observaciones;
    private Long ubigeo;
    private Long negocio;
    private Long empleado;
}