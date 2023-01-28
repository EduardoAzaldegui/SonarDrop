package com.app.drop.empleado.domain.service;

import com.app.drop.empleado.domain.model.entity.Employed;
import com.app.drop.empleado.resource.EmployedResource;

import java.util.List;

public interface EmployedService {
    List<Employed> getAll();
    Employed getEmployedById(Long id);
    Employed create(Employed empleado, Long idBusiness);
    Employed update(Long id, Employed empleadoInput);
    Employed delete(Long id);
    Employed patch(Long id,char status);

}
