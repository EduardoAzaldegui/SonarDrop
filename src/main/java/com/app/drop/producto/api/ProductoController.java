package com.app.drop.producto.api;

import com.app.drop.producto.domain.model.entity.Producto;
import com.app.drop.producto.domain.service.ProductoService;
import com.app.drop.producto.mapping.ProductoMapper;
import com.app.drop.producto.resource.CreateProductoResource;
import com.app.drop.producto.resource.ProductoResource;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/dev/productos", produces = "application/json")
@AllArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    private final ProductoMapper productoMapper;

    @GetMapping
    @Operation(summary = "Obtener todos los productos")
    public Page<ProductoResource> getAllProducts(@ParameterObject Pageable pageable) {
        List<Producto> productos = productoService.getAll();
        return productoMapper.modelListPage(productos
                        .stream()
                        .map(product -> productoMapper.toResource(product)).collect(Collectors.toList())
                , pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto por id")
    public ProductoResource getProductById(@PathVariable Long id) {
        Producto producto = productoService.getProductById(id);
        return productoMapper.toResource(producto);
    }

    @PostMapping
    @Operation(summary = "Crear un producto")
    public ResponseEntity<ProductoResource> createProduct(@RequestBody CreateProductoResource resource
            , @RequestParam Long idNegocio, @RequestParam Long idCategoria) {
        Producto productoInput = productoMapper.toModel(resource);
        Producto productoSaved = productoService.create(productoInput,idNegocio,idCategoria);
        ProductoResource productoResource = productoMapper.toResource(productoSaved);
        return new ResponseEntity<>(productoResource, HttpStatus.CREATED); // 201
    }

    @PatchMapping("/{id}/{idCategoria}")
    @Operation(summary = "Actualizar un producto")
    public ResponseEntity<ProductoResource> updateProduct(@RequestBody CreateProductoResource resource
            , @PathVariable int id , @PathVariable  int idCategoria) {
        Producto productoInput = productoMapper.toModel(resource);
        Producto productoUpdated = productoService.update((long) id, productoInput, (long) idCategoria);
        ProductoResource productoResource = productoMapper.toResource(productoUpdated);
        return new ResponseEntity<>(productoResource, HttpStatus.OK); // 200
    }

    @PatchMapping("activar/{id}")
    @Operation(summary = "Activar un producto")
    public ResponseEntity activate(@PathVariable Long id) {
        productoService.patch(id,'A');
        return new ResponseEntity<>(HttpStatus.OK); // 200
    }

    @PatchMapping("desactivar/{id}")
    @Operation(summary = "Desactivar un producto")
    public ResponseEntity deactivated(@PathVariable Long id) {
        productoService.patch(id,'I');
        return new ResponseEntity<>(HttpStatus.OK); // 200
    }

}
