package com.app.drop.producto.domain.service;

import com.app.drop.producto.domain.model.entity.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> getAll();
    Producto getProductById(Long id);
    Producto create(Producto producto, Long idbusiness, Long idCategories);
    Producto update(Long id, Producto productoInput, Long idCategories);
    Producto delete(Long id);
    Producto patch(Long id, char status);
}
