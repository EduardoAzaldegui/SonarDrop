package com.app.drop.listaDeseo.api;

import com.app.drop.listaDeseo.domain.model.entity.ListaDeseo;
import com.app.drop.listaDeseo.domain.service.ListaDeseoService;
import com.app.drop.listaDeseo.mapping.ListaDeseoMapper;
import com.app.drop.listaDeseo.resource.CreateListaDeseoResource;
import com.app.drop.listaDeseo.resource.ListaDeseoResource;
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
@RequestMapping(value = "api/dev/listaDeseos", produces = "application/json")
@AllArgsConstructor
public class ListaDeseoController {

    private final ListaDeseoService listaDeseoService;

    private final ListaDeseoMapper listaDeseoMapper;

    @GetMapping
    @Operation(summary = "Obtener todos las lista de deseos")
    public Page<ListaDeseoResource> getAll(@ParameterObject Pageable pageable) {
        List<ListaDeseo> listaDeseos = listaDeseoService.getAll();
        return listaDeseoMapper.modelListPage(listaDeseos
                        .stream()
                        .map(listaDeseo -> listaDeseoMapper.toResource(listaDeseo)).collect(Collectors.toList()), pageable);
    }

    @PostMapping
    @Operation(summary = "Crear una lista de deseo")
    public ResponseEntity<ListaDeseoResource> create(@RequestBody CreateListaDeseoResource resource ,
        @RequestParam Long idbusiness,@RequestParam Long idProduct) {
        ListaDeseo listaDeseoInput = listaDeseoMapper.toModel(resource);
        ListaDeseo listaDeseoSaved = listaDeseoService.create(listaDeseoInput,idbusiness,idProduct);
        ListaDeseoResource listaDeseoResource = listaDeseoMapper.toResource(listaDeseoSaved);
        return new ResponseEntity<>(listaDeseoResource, HttpStatus.CREATED); // 201
    }

    @PatchMapping("activar/{id}")
    @Operation(summary = "Activar una listaDeseo")
    public ResponseEntity activateWishList(@PathVariable Long id) {
        listaDeseoService.patch(id,'A');
        return new ResponseEntity<>(HttpStatus.OK); // 200
    }

    @PatchMapping("desactivar/{id}")
    @Operation(summary = "Desactivar una listaDeseo")
    public ResponseEntity deactivatedWishList(@PathVariable Long id) {
        listaDeseoService.patch(id,'I');
        return new ResponseEntity<>(HttpStatus.OK); // 200
    }

}
