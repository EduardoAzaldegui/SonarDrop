package com.app.drop.Ubigeo.domain.service;

import com.app.drop.Ubigeo.domain.model.entity.Ubigeo;
import com.app.drop.rol.domain.model.entity.Rol;
import java.util.List;

public interface UbigeoService {
    List<Ubigeo> getAll();
    Ubigeo getUbigeoById(Long id);
    Ubigeo create(Ubigeo ubigeo);
    Ubigeo update(Long id, Ubigeo ubigeoInput);
    Ubigeo delete(Long id);

}
