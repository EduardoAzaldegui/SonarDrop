package com.app.drop.negocio.api;

import com.app.drop.negocio.domain.model.entity.Negocio;
import com.app.drop.negocio.domain.service.NegocioService;
import com.app.drop.negocio.mapping.NegocioMapper;
import com.app.drop.negocio.resource.NegocioResource;
import com.app.drop.negocio.resource.CreateNegocioResource;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/dev/negocios", produces = "application/json")
@AllArgsConstructor
public class NegocioController {

    private final NegocioService negocioService;

    private final NegocioMapper negocioMapper;

    @GetMapping
    @Operation(summary = "Obtener todos los negocios")
    public Page<NegocioResource> getAll(@ParameterObject Pageable pageable) {
        List<Negocio> negocios = negocioService.getAll();
        return negocioMapper.modelListPage(negocios
                .stream()
                .map(negocio -> negocioMapper.toResource(negocio)).collect(Collectors.toList()), pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener negocio por id")
    public NegocioResource getById(@PathVariable Long id) {
        Negocio negocio = negocioService.getNegocioById(id);
        return negocioMapper.toResource(negocio);
    }

    @PostMapping
    @Operation(summary = "Crear un negocio")
    public ResponseEntity<NegocioResource> createBusiness(@RequestBody CreateNegocioResource resource) {
        Negocio negocioInput = negocioMapper.toModel(resource);
        negocioInput.setEstado('A');
        Negocio negocioSaved = negocioService.create(negocioInput);
        NegocioResource negocioResource = negocioMapper.toResource(negocioSaved);
        negocioResource.setEmpleados(new ArrayList<>());
        negocioResource.setProductos(new ArrayList<>());
        return new ResponseEntity<>(negocioResource, HttpStatus.CREATED); // 201
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un negocio")
    public ResponseEntity<NegocioResource> updateBusiness(@PathVariable Long id, @RequestBody CreateNegocioResource resource) {
        Negocio negocioInput = negocioMapper.toModel(resource);
        Negocio negocioUpdated = negocioService.update(id, negocioInput);
        NegocioResource negocioResource = negocioMapper.toResource(negocioUpdated);
        return new ResponseEntity<>(negocioResource, HttpStatus.OK); // 200
    }

    @PatchMapping("activar/{id}")
    @Operation(summary = "Activar un negocio")
    public ResponseEntity activate(@PathVariable Long id) {
        negocioService.patch(id,'A');
        return new ResponseEntity<>(HttpStatus.OK); // 200
    }

    @PatchMapping("desactivar/{id}")
    @Operation(summary = "Desactivar un negocio")
    public ResponseEntity deactivated(@PathVariable Long id) {
        negocioService.patch(id,'I');
        return new ResponseEntity<>(HttpStatus.OK); // 200
    }

}
