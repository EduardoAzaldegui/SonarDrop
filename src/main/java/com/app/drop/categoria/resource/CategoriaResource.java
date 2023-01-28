package com.app.drop.categoria.resource;

import lombok.*;

import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaResource {
    private Long id;
    private String nombre;
    private List<Long> productos;
}