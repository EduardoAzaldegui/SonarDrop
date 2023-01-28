package com.app.drop.Ubigeo.api;

import com.app.drop.Ubigeo.domain.model.entity.Ubigeo;
import com.app.drop.Ubigeo.domain.service.UbigeoService;
import com.app.drop.Ubigeo.mapping.UbigeoMapper;
import com.app.drop.Ubigeo.resource.CreateUbigeoResource;
import com.app.drop.Ubigeo.resource.UbigeoResource;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/dev/ubigeo", produces = "application/json")
@AllArgsConstructor
public class UbigeoController {
    private final UbigeoService ubigeoService;
    private final UbigeoMapper ubigeoMapper;

    @GetMapping
    @Operation(summary = "Obtener todos los ubigeos")
    public Page<UbigeoResource> getAllRol(@ParameterObject Pageable pageable) {
        return ubigeoMapper.modelListPage(ubigeoService.getAll(), pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener ubigeos por id")
    public UbigeoResource getRolById(@PathVariable Long id) {
        return ubigeoMapper.toResource(ubigeoService.getUbigeoById(id));
    }

    @PostMapping
    @Operation(summary = "Crear un ubigeos")
    public ResponseEntity<UbigeoResource> createRol(@RequestBody CreateUbigeoResource resource) {
        Ubigeo ubigeoInput = ubigeoMapper.toModel(resource);
        ubigeoInput.setEstado("A");
        Ubigeo ubigeoSaved = ubigeoService.create(ubigeoInput);
        UbigeoResource ubigeoResource = ubigeoMapper.toResource(ubigeoSaved);
        return new ResponseEntity<>(ubigeoResource, HttpStatus.CREATED); // 201
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un ubigeos")
    public ResponseEntity<UbigeoResource> updateRol(@PathVariable Long id, @RequestBody CreateUbigeoResource resource) {
        Ubigeo ubigeoInput = ubigeoMapper.toModel(resource);
        Ubigeo ubigeoUpdate = ubigeoService.update(id, ubigeoInput);
        UbigeoResource ubigeoResource = ubigeoMapper.toResource(ubigeoUpdate);
        return new ResponseEntity<>(ubigeoResource, HttpStatus.OK); // 200
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un ubigeos")
    public ResponseEntity<UbigeoResource> deleteRol(@PathVariable Long id) {
        Ubigeo ubigeo = ubigeoService.delete(id);
        UbigeoResource ubigeoResource = ubigeoMapper.toResource(ubigeo);
        return new ResponseEntity<>(ubigeoResource, HttpStatus.OK); // 200
    }


}
