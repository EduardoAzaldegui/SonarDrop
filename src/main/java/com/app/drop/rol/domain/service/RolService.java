package com.app.drop.rol.domain.service;

import com.app.drop.rol.domain.model.entity.Rol;

import java.util.List;

public interface RolService {
    List<Rol> getAll();
    Rol getRolById(Long id);
    Rol create(Rol rol);
    Rol update(Long id, Rol rolInput);
    Rol delete(Long id);

}
