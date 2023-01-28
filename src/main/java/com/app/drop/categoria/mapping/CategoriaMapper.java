package com.app.drop.categoria.mapping;

import com.app.drop.categoria.domain.model.entity.Categoria;
import com.app.drop.categoria.resource.CreateCategoriaResource;
import com.app.drop.categoria.resource.CategoriaResource;
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
public class CategoriaMapper implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private EnhancedModelMapper modelMapper;

    public CategoriaMapper categoriaMapper() {
        return new CategoriaMapper(modelMapper);
    }

    public Page<CategoriaResource> modelListPage(List<CategoriaResource> modelList, Pageable pageable) {
        return new PageImpl<>(modelMapper.mapList(modelList, CategoriaResource.class), pageable, modelList.size());
    }

    public Categoria toModel(CreateCategoriaResource resource) {
        return modelMapper.map(resource, Categoria.class);
    }

    public CategoriaResource toResource(Categoria categoria) {
        CategoriaResource categoriaResource = new CategoriaResource();
        categoriaResource.setId(categoria.getId());
        categoriaResource.setNombre(categoria.getNombre());
        categoriaResource.setProductos(categoria.getProductos().stream().map(product -> product.getId()).collect(Collectors.toList()));
        return categoriaResource;
    }


}

