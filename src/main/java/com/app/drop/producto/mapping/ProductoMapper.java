package com.app.drop.producto.mapping;

import com.app.drop.categoria.resource.ManyToOneCategoriaResource;
import com.app.drop.negocio.resource.ManyToOneNegocioResource;
import com.app.drop.producto.domain.model.entity.Producto;
import com.app.drop.producto.resource.CreateProductoResource;
import com.app.drop.producto.resource.ProductoResource;
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
public class ProductoMapper implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private EnhancedModelMapper modelMapper;

    public ProductoMapper productMapper() {
        return new ProductoMapper(modelMapper);
    }

    public Page<ProductoResource> modelListPage(List<ProductoResource> modelList, Pageable pageable) {
        return new PageImpl<>(modelMapper.mapList(modelList, ProductoResource.class), pageable, modelList.size());
    }

    public Producto toModel(CreateProductoResource resource) {
        return modelMapper.map(resource, Producto.class);
    }

    public ProductoResource toResource(Producto producto) {
        ProductoResource productoResource = new ProductoResource();
        productoResource.setId(producto.getId());
        productoResource.setNombre(producto.getNombre());
        productoResource.setStock(producto.getStock());
        productoResource.setImagen(producto.getImagen());
        productoResource.setPrecioVenta(producto.getPrecioVenta());
        productoResource.setListaDeseos(producto.getListaDeseos().stream().map(listDeseo -> listDeseo.getId()).collect(Collectors.toList()));

        ManyToOneNegocioResource manyToOneNegocioResource = new ManyToOneNegocioResource();
        manyToOneNegocioResource.setId(producto.getNegocio().getId());
        manyToOneNegocioResource.setNombre(producto.getNegocio().getNombre());
        productoResource.setNegocio(manyToOneNegocioResource);

        ManyToOneCategoriaResource manyToOneCategoriaResource = new ManyToOneCategoriaResource();
        manyToOneCategoriaResource.setId(producto.getCategoria().getId());
        manyToOneCategoriaResource.setNombre(producto.getCategoria().getNombre());
        productoResource.setCategoria(manyToOneCategoriaResource);

        return productoResource;
    }


}

