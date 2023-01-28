package com.app.drop.empleado.api;

import com.app.drop.empleado.domain.model.entity.Employed;
import com.app.drop.empleado.domain.service.EmployedService;
import com.app.drop.empleado.mapping.EmployedMapper;
import com.app.drop.empleado.resource.CreateEmployedResource;
import com.app.drop.empleado.resource.EmployedResource;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "api/dev/empleados", produces = "application/json")
public class EmpleadoController {

    private final EmployedService employedService;
    private final EmployedMapper employedMapper;


    //METODOS IMPLEMENTADOS
    @GetMapping
    @Operation(summary = "Obtener todas los Empleados")
    public Page<EmployedResource> getAll(@ParameterObject Pageable pageable) {
        List<Employed> empleados = employedService.getAll();
        return employedMapper.modelListPage(empleados
                .stream()
                .map(empleado -> employedMapper.toResource(empleado)).collect(Collectors.toList()), pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener empleado por id")
    public EmployedResource getById(@PathVariable Long id) {
        Employed empleado = employedService.getEmployedById(id);
        return employedMapper.toResource(empleado);
    }

    @PostMapping
    @Operation(summary = "Crear un empleado")
    public ResponseEntity<EmployedResource> create(@RequestBody CreateEmployedResource resource, @RequestParam Long idBusiness) {
        Employed employedInput = employedMapper.toModel(resource);
        Employed employedSaved = employedService.create(employedInput,idBusiness);
        EmployedResource employedResource = employedMapper.toResource(employedSaved);
        return new ResponseEntity<>(employedResource, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un empleado")
    public ResponseEntity<EmployedResource> update(@PathVariable Long id, @RequestBody CreateEmployedResource resource) {
        Employed employedInput = employedMapper.toModel(resource);
        Employed employedSaved = employedService.update(id, employedInput);
        EmployedResource employedResource = employedMapper.toResource(employedSaved);
        return new ResponseEntity<>(employedResource, HttpStatus.OK);
    }

//    @DeleteMapping("/{id}")
//    @Operation(summary = "Eliminar un empleado")
//    public ResponseEntity<EmployedResource> deleteEmployed(@PathVariable Long id) {
//        Employed employed = employedService.delete(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

    @PatchMapping("activar/{id}")
    @Operation(summary = "Activar un empleado")
    public ResponseEntity activate(@PathVariable Long id) {
         employedService.patch(id,'A');
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("desactivar/{id}")
    @Operation(summary = "Desactivar un empleado")
    public ResponseEntity deactivated(@PathVariable Long id) {
        employedService.patch(id, 'I');
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
