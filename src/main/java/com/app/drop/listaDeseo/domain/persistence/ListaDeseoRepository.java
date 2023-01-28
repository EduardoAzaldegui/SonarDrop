package com.app.drop.listaDeseo.domain.persistence;

import com.app.drop.listaDeseo.domain.model.entity.ListaDeseo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListaDeseoRepository extends JpaRepository<ListaDeseo,Long> {
    @Query("SELECT c FROM ListaDeseo c WHERE c.estado = 'A'")
    public List<ListaDeseo> findAllActive();


}
