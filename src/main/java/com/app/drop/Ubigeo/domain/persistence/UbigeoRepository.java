package com.app.drop.Ubigeo.domain.persistence;

import com.app.drop.Ubigeo.domain.model.entity.Ubigeo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UbigeoRepository extends JpaRepository<Ubigeo,Long> {

}
