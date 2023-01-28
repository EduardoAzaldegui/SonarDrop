package com.app.drop.categoria.api;

import com.app.drop.categoria.domain.model.entity.Categoria;
import com.app.drop.categoria.domain.service.CategoriaService;
import com.app.drop.categoria.mapping.CategoriaMapper;
import com.app.drop.categoria.resource.CreateCategoriaResource;
import com.app.drop.categoria.resource.CategoriaResource;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/dev/categorias", produces = "application/json")
@AllArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    private final CategoriaMapper categoriaMapper;

    @GetMapping
    @Operation(summary = "Obtener todas las categorias")
    public Page<CategoriaResource> getAll(@ParameterObject Pageable pageable) {
        List<Categoria> categorias = categoriaService.getAll();
        return categoriaMapper.modelListPage(categorias
                .stream()
                .map(categoria -> categoriaMapper.toResource(categoria)).collect(Collectors.toList()), pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener categoria por id")
    public CategoriaResource getById(@PathVariable Long id) {
        Categoria categoria = categoriaService.getCategoriaById(id);
        return categoriaMapper.toResource(categoria);
    }

    @PostMapping
    @Operation(summary = "Crear una categoria")
    public ResponseEntity<CategoriaResource> create(@RequestBody CreateCategoriaResource resource) {
        Categoria categoriaInput = categoriaMapper.toModel(resource);
        categoriaInput.setEstado('A');
        Categoria categoriaSaved = categoriaService.create(categoriaInput);
        CategoriaResource categoriaResource = categoriaMapper.toResource(categoriaSaved);
        categoriaResource.setProductos(new ArrayList<>());
        return new ResponseEntity<>(categoriaResource, HttpStatus.CREATED); // 201
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una categoria")
    public ResponseEntity<CategoriaResource> update(@PathVariable Long id, @RequestBody CreateCategoriaResource resource) {
        Categoria categoriaInput = categoriaMapper.toModel(resource);
        Categoria categoriaUpdated = categoriaService.update(id, categoriaInput);
        CategoriaResource categoriaResource = categoriaMapper.toResource(categoriaUpdated);
        return new ResponseEntity<>(categoriaResource, HttpStatus.OK); // 200
    }

    @PatchMapping("activar/{id}")
    @Operation(summary = "Activar una categoria")
    public ResponseEntity activate(@PathVariable Long id) {
        categoriaService.patch(id,'A');
        return new ResponseEntity<>(HttpStatus.OK); // 200
    }

    @PatchMapping("desactivar/{id}")
    @Operation(summary = "Desactivar una categoria")
    public ResponseEntity deactivated(@PathVariable Long id) {
        categoriaService.patch(id,'I');
        return new ResponseEntity<>(HttpStatus.OK); // 200
    }

}
