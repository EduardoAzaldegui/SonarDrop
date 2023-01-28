package com.app.drop.pedido.mapping;

import com.app.drop.pedido.domain.model.entity.Pedido;
import com.app.drop.pedido.resource.CreatePedidoResource;
import com.app.drop.pedido.resource.PedidoResource;
import com.app.drop.shared.mapping.EnhancedModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;


@Component
@AllArgsConstructor
public class PedidoMapper implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private EnhancedModelMapper modelMapper;

    public PedidoMapper pedidoMapper() {
        return new PedidoMapper(modelMapper);
    }

    public Page<PedidoResource> modelListPage(List<PedidoResource> modelList, Pageable pageable) {
        return new PageImpl<>(modelMapper.mapList(modelList, PedidoResource.class), pageable, modelList.size());
    }

    public Pedido toModel(CreatePedidoResource resource) {
        return modelMapper.map(resource, Pedido.class);
    }

    public PedidoResource toResource(Pedido pedido) {
        PedidoResource pedidoResource = new PedidoResource();
        pedidoResource.setId(pedido.getId());
        pedidoResource.setNombre(pedido.getNombre());
        pedidoResource.setDireccion(pedido.getDireccion());
        pedidoResource.setTelefono(pedido.getTelefono());
        pedidoResource.setTelefono(pedido.getTelefono());
        pedidoResource.setEstadoPedido(pedido.getEstadoPedido());
        pedidoResource.setEstadoAsignacion(pedido.getEstadoAsignacion());
        pedidoResource.setFechaPedido(pedido.getFechaPedido());
        pedidoResource.setFechaRegistroPedido(pedido.getFechaRegistroPedido());
        pedidoResource.setObservaciones(pedido.getObservaciones());
        pedidoResource.setEstadoPago(pedido.getEstadoPago());
        pedidoResource.setObservacionesEntrega(pedido.getObservacionesEntrega());
        pedidoResource.setUbigeo(pedido.getUbigeo().getId());
        pedidoResource.setNegocio(pedido.getNegocio().getId());
        pedidoResource.setEmpleado(pedido.getEmpleado().getIdEmp());
        return pedidoResource;
    }


}

