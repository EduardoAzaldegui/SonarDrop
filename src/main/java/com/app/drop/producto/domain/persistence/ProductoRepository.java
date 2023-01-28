package com.app.drop.producto.domain.persistence;

import com.app.drop.producto.domain.model.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {
    @Query("SELECT c FROM Producto c WHERE c.estado = 'A'")
    public List<Producto> findAllActive();

    @Query("SELECT c FROM Producto c WHERE c.estado = 'A' AND c.id = ?1")
    public Optional<Producto> findActiveById(Long id);

}
