package com.app.drop.rol.api;

import com.app.drop.rol.domain.model.entity.Rol;
import com.app.drop.rol.domain.service.RolService;
import com.app.drop.rol.mapping.RolMapper;
import com.app.drop.rol.resource.CreateRolResource;
import com.app.drop.rol.resource.RolResource;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/dev/rol", produces = "application/json")
@AllArgsConstructor
public class RolController {

    private final RolService rolService;

    private final RolMapper rolMapper;

    @GetMapping
    @Operation(summary = "Obtener todos los roles")
    public Page<RolResource> getAllRol(@ParameterObject Pageable pageable) {
        return rolMapper.modelListPage(rolService.getAll(), pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener rol por id")
    public RolResource getRolById(@PathVariable Long id) {
        return rolMapper.toResource(rolService.getRolById(id));
    }

    @PostMapping
    @Operation(summary = "Crear un rol")
    public ResponseEntity<RolResource> createRol(@RequestBody CreateRolResource resource) {
        Rol rolInput = rolMapper.toModel(resource);
        Rol rolSaved = rolService.create(rolInput);
        RolResource rolResource = rolMapper.toResource(rolSaved);
        return new ResponseEntity<>(rolResource, HttpStatus.CREATED); // 201
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un rol")
    public ResponseEntity<RolResource> updateRol(@PathVariable Long id, @RequestBody CreateRolResource resource) {
        Rol rolInput = rolMapper.toModel(resource);
        Rol rolUpdate = rolService.update(id, rolInput);
        RolResource rolResource = rolMapper.toResource(rolUpdate);
        return new ResponseEntity<>(rolResource, HttpStatus.OK); // 200
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un rol")
    public ResponseEntity<RolResource> deleteRol(@PathVariable Long id) {
        Rol rol = rolService.delete(id);
        RolResource rolResource = rolMapper.toResource(rol);
        return new ResponseEntity<>(rolResource, HttpStatus.OK); // 200
    }

}
