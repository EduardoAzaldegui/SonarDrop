package com.app.drop.rol.domain.persistence;

import com.app.drop.rol.domain.model.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<Rol,Long> {
    @Query("SELECT ar FROM Rol ar WHERE ar.estado = 'A'")
    List<Rol> findAllActive();
}