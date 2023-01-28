package com.app.drop.pedido.resource;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class PedidoResource {
    private Long id;
    private String nombre;
    private String direccion;
    private String referencia;
    private String telefono;
    private String estadoPedido;
    private String estadoAsignacion;
    private Date fechaPedido;
    private Date fechaRegistroPedido;
    private String observaciones;
    private String estadoPago;
    private String observacionesEntrega;
    private Long ubigeo;
    private Long negocio;
    private Long empleado;
}