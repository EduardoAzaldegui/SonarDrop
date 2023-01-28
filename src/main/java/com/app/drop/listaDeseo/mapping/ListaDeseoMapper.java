package com.app.drop.listaDeseo.mapping;

import com.app.drop.listaDeseo.domain.model.entity.ListaDeseo;
import com.app.drop.listaDeseo.resource.CreateListaDeseoResource;
import com.app.drop.listaDeseo.resource.ListaDeseoResource;
import com.app.drop.shared.mapping.EnhancedModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@AllArgsConstructor
public class ListaDeseoMapper implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private EnhancedModelMapper modelMapper;

    public ListaDeseoMapper listaDeseoMapper() {
        return new ListaDeseoMapper(modelMapper);
    }

    public Page<ListaDeseoResource> modelListPage(List<ListaDeseoResource> modelList, Pageable pageable) {
        return new PageImpl<>(modelMapper.mapList(modelList, ListaDeseoResource.class), pageable, modelList.size());
    }

    public ListaDeseo toModel(CreateListaDeseoResource resource) {
        return modelMapper.map(resource, ListaDeseo.class);
    }

    public ListaDeseoResource toResource(ListaDeseo listaDeseo) {
        ListaDeseoResource listaDeseoResource = new ListaDeseoResource();
        listaDeseoResource.setId(listaDeseo.getId());
        listaDeseoResource.setNegocio(listaDeseo.getNegocio().getId());
        listaDeseoResource.setProducto(listaDeseo.getProducto().getId());
        return listaDeseoResource;
    }
}

