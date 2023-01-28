package com.app.drop.listaDeseo.domain.service;

import com.app.drop.listaDeseo.domain.model.entity.ListaDeseo;

import java.util.List;

public interface ListaDeseoService {
    List<ListaDeseo> getAll();
    ListaDeseo create(ListaDeseo listaDeseo, Long idNegocio, Long idProducto);
    ListaDeseo delete(Long id);
    ListaDeseo patch(Long id, char status);
}
