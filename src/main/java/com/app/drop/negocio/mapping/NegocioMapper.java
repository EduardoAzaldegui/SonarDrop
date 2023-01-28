package com.app.drop.negocio.mapping;

import com.app.drop.negocio.domain.model.entity.Negocio;
import com.app.drop.negocio.resource.CreateNegocioResource;
import com.app.drop.negocio.resource.NegocioResource;
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
public class NegocioMapper implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private EnhancedModelMapper modelMapper;

    public NegocioMapper negocioMapper() {
        return new NegocioMapper(modelMapper);
    }

    public Page<NegocioResource> modelListPage(List<NegocioResource> modelList, Pageable pageable) {
        return new PageImpl<>(modelMapper.mapList(modelList, NegocioResource.class), pageable, modelList.size());
    }

    public Negocio toModel(CreateNegocioResource resource) {
        return modelMapper.map(resource, Negocio.class);
    }

    public NegocioResource toResource(Negocio negocio) {
        NegocioResource negocioResource = new NegocioResource();
        negocioResource.setId(negocio.getId());
        negocioResource.setTipo(negocio.getTipo());
        negocioResource.setNombre(negocio.getNombre());
        negocioResource.setPaginaWeb(negocio.getPaginaWeb());
        negocioResource.setContacto(negocio.getContacto());
        negocioResource.setNumeroContacto(negocio.getNumeroContacto());
        negocioResource.setLogo(negocio.getLogo());
        negocioResource.setEmail(negocio.getEmail());
        negocioResource.setRuc(negocio.getRuc());
        negocioResource.setProductos(negocio.getProductos().stream().map(producto -> producto.getId()).collect(Collectors.toList()));
        negocioResource.setEmpleados(negocio.getEmpleados().stream().map(empleado -> empleado.getIdEmp()).collect(Collectors.toList()));
        negocioResource.setListaDeseos(negocio.getListaDeseos().stream().map(listaDeseo -> listaDeseo.getId()).collect(Collectors.toList()));
        return negocioResource;
    }


}

