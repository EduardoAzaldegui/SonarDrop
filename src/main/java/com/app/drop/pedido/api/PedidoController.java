package com.app.drop.pedido.api;

import com.app.drop.pedido.domain.model.entity.Pedido;
import com.app.drop.pedido.domain.service.PedidoService;
import com.app.drop.pedido.mapping.PedidoMapper;
import com.app.drop.pedido.resource.CreatePedidoResource;
import com.app.drop.pedido.resource.PedidoResource;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/dev/pedidos", produces = "application/json")
@AllArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    private final PedidoMapper pedidoMapper;

    @GetMapping
    @Operation(summary = "Obtener todos las pedidos")
    public Page<PedidoResource> getAll(@ParameterObject Pageable pageable) {
        List<Pedido> pedidos = pedidoService.getAll();
        return pedidoMapper.modelListPage(pedidos
                .stream()
                .map(pedido -> pedidoMapper.toResource(pedido)).collect(Collectors.toList()), pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener pedido por id")
    public PedidoResource getById(@PathVariable Long id) {
        Pedido pedido =pedidoService.getPedidoById(id);
        return pedidoMapper.toResource(pedido);
    }

    @PostMapping
    @Operation(summary = "Crear un pedido")
    public ResponseEntity<PedidoResource> create(@RequestBody CreatePedidoResource resource) {
        Pedido pedidoInput = pedidoMapper.toModel(resource);
        Pedido pedidoSaved = pedidoService.create(pedidoInput);
        PedidoResource pedidoResource = pedidoMapper.toResource(pedidoSaved);
        return new ResponseEntity<>(pedidoResource, HttpStatus.CREATED); // 201
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un pedido")
    public ResponseEntity<PedidoResource> deleteProduct(@PathVariable Long id) {
        Pedido Pedido = pedidoService.delete(id);
        PedidoResource PedidoResource = pedidoMapper.toResource(Pedido);
        return new ResponseEntity<>(PedidoResource, HttpStatus.OK); // 200
    }

}
