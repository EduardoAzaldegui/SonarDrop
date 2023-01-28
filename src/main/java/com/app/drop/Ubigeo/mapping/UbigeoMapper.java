package com.app.drop.Ubigeo.mapping;

import com.app.drop.Ubigeo.domain.model.entity.Ubigeo;
import com.app.drop.Ubigeo.resource.CreateUbigeoResource;
import com.app.drop.Ubigeo.resource.UbigeoResource;
import com.app.drop.shared.mapping.EnhancedModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UbigeoMapper {

    private static final long serialVersionUID = 1L;

    @Autowired
    private EnhancedModelMapper modelMapper;

    public UbigeoMapper ubigeoMapper() {
        return new UbigeoMapper(modelMapper);
    }

    public Page<UbigeoResource> modelListPage(List<Ubigeo> modelList, Pageable pageable) {
        return new PageImpl<>(modelMapper.mapList(modelList, UbigeoResource.class), pageable, modelList.size());
    }

    public Ubigeo toModel(CreateUbigeoResource resource) {
        return modelMapper.map(resource, Ubigeo.class);
    }

    public UbigeoResource toResource(Ubigeo ubigeo) {
        return modelMapper.map(ubigeo, UbigeoResource.class);
    }

}
