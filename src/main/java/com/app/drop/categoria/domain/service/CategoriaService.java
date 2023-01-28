package com.app.drop.categoria.domain.service;

import com.app.drop.categoria.domain.model.entity.Categoria;

import java.util.List;

public interface CategoriaService {
    List<Categoria> getAll();
    Categoria getCategoriaById(Long id);
    Categoria create(Categoria categoria);
    Categoria update(Long id, Categoria categoriaInput);
    Categoria delete(Long id);
    Categoria patch(Long id, char status);
}
