package com.app.drop.categoria.domain.persistence;

import com.app.drop.categoria.domain.model.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
    @Query("SELECT c FROM Categoria c WHERE c.estado = 'A'")
    public List<Categoria> findAllActive();

    @Query("SELECT c FROM Categoria c WHERE c.estado = 'A' AND c.id = ?1")
    public Optional<Categoria> findActiveById(Long id);
}
