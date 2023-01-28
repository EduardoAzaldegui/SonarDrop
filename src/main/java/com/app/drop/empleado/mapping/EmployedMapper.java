package com.app.drop.empleado.mapping;

import com.app.drop.empleado.domain.model.entity.Employed;
import com.app.drop.empleado.resource.CreateEmployedResource;
import com.app.drop.empleado.resource.EmployedResource;
import com.app.drop.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Component
@AllArgsConstructor
public class EmployedMapper implements Serializable{
    private static final long serialVersionUID = 1L;

    @Autowired
    private EnhancedModelMapper modelMapper;

    public EmployedMapper employedMapper(){
        return new EmployedMapper(modelMapper);
    }

    public Page<EmployedResource> modelListPage(List<EmployedResource> modelList, Pageable pageable) {
        return new PageImpl<>(modelMapper.mapList(modelList, EmployedResource.class), pageable, modelList.size());
    }

    public Employed toModel(CreateEmployedResource resource) {
        return modelMapper.map(resource, Employed.class);
    }

    public EmployedResource toResource(Employed employed) {
        EmployedResource employedResource = new EmployedResource();
        employedResource.setIdEmp(employed.getIdEmp());
        employedResource.setNomEmp(employed.getNomEmp());
        employedResource.setApeEmp(employed.getApeEmp());
        employedResource.setUsuEmp(employed.getUsuEmp());
        employedResource.setPassEmp(employed.getPassEmp());
        employedResource.setIdNeg(employed.getNegocio().getId());
        return employedResource;
    }

}
