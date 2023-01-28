package com.app.drop.producto.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductoResource {
    private String nombre;
    private Integer stock;
    private String imagen;
    private Double precioVenta;

}