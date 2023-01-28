package com.app.drop.producto.resource;

import com.app.drop.categoria.resource.ManyToOneCategoriaResource;
import com.app.drop.negocio.resource.ManyToOneNegocioResource;
import lombok.*;

import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ProductoResource {
    private Long id;
    private String nombre;
    private Integer stock;
    private String imagen;
    private Double precioVenta;
    private ManyToOneNegocioResource negocio;
    private ManyToOneCategoriaResource categoria;
    private List<Long> listaDeseos;

}