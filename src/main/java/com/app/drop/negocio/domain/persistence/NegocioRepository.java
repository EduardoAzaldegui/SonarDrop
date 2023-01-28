package com.app.drop.negocio.domain.persistence;

import com.app.drop.negocio.domain.model.entity.Negocio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NegocioRepository extends JpaRepository<Negocio,Long> {
    @Query("SELECT c FROM Negocio c WHERE c.estado = 'A'")
    public List<Negocio> findAllActive();

    @Query("SELECT c FROM Negocio c WHERE c.estado = 'A' AND c.id = ?1")
    public Optional<Negocio> findActiveById(Long id);
}
