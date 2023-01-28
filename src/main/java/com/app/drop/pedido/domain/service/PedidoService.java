package com.app.drop.pedido.domain.service;

import com.app.drop.pedido.domain.model.entity.Pedido;

import java.util.List;

public interface PedidoService {
    List<Pedido> getAll();
    Pedido getPedidoById(Long id);
    Pedido create(Pedido pedido);
    Pedido delete(Long id);
}
