package com.app.drop.rol.mapping;

import com.app.drop.rol.domain.model.entity.Rol;
import com.app.drop.rol.resource.CreateRolResource;
import com.app.drop.rol.resource.RolResource;
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
public class RolMapper {

    private static final long serialVersionUID = 1L;

    @Autowired
    private EnhancedModelMapper modelMapper;

    public RolMapper rolMapper() {
        return new RolMapper(modelMapper);
    }

    public Page<RolResource> modelListPage(List<Rol> modelList, Pageable pageable) {
        return new PageImpl<>(modelMapper.mapList(modelList, RolResource.class), pageable, modelList.size());
    }

    public Rol toModel(CreateRolResource resource) {
        return modelMapper.map(resource, Rol.class);
    }

    public RolResource toResource(Rol rol) {
        return modelMapper.map(rol, RolResource.class);
    }

}
