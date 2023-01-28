package com.app.drop.negocio.domain.service;


import com.app.drop.negocio.domain.model.entity.Negocio;

import java.util.List;

public interface NegocioService {
    List<Negocio> getAll();
    Negocio getNegocioById(Long id);
    Negocio create(Negocio negocio);
    Negocio update(Long id, Negocio negocioInput);
    Negocio delete(Long id);
    Negocio patch(Long id, char status);
}
