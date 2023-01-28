package com.app.drop.empleado.domain.persistence;

import com.app.drop.empleado.domain.model.entity.Employed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployedRepository extends JpaRepository<Employed, Long> {
    @Query("SELECT c FROM Employed c WHERE c.estEmp = 'A'")
    public List<Employed> findAllActive();

    @Query("SELECT c FROM Employed c WHERE c.estEmp = 'A' AND c.idEmp = ?1")
    public Optional<Employed> findActiveById(Long id);
}
