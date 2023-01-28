package com.app.drop.Ubigeo.service;


import com.app.drop.Ubigeo.domain.model.entity.Ubigeo;
import com.app.drop.Ubigeo.domain.persistence.UbigeoRepository;
import com.app.drop.Ubigeo.domain.service.UbigeoService;
import com.app.drop.shared.exception.ResourceValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UbigeoServiceImpl implements UbigeoService {

    private static final String ENTITY = "Ubigeo";
    private final UbigeoRepository ubigeoRepository;
    private final Validator validator;


    @Override
    public List<Ubigeo> getAll() {
        return ubigeoRepository.findAll();
    }

    @Override
    public Ubigeo getUbigeoById(Long id) {
        return ubigeoRepository.findById(id).orElseThrow(() -> new ResourceValidationException(ENTITY, "id", id));
    }

    @Override
    public Ubigeo create(Ubigeo ubigeo) {
        Set<ConstraintViolation<Ubigeo>> violations = validator.validate(ubigeo);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        return ubigeoRepository.save(ubigeo);
    }

    @Override
    public Ubigeo update(Long id, Ubigeo ubigeoInput) {
        Set<ConstraintViolation<Ubigeo>> violations = validator.validate(ubigeoInput);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        Ubigeo ubigeo = ubigeoRepository.findById(id).orElseThrow(() -> new ResourceValidationException(ENTITY, "id", id));
        ubigeoInput.setId(ubigeo.getId());
        ubigeoInput.setEstado(ubigeo.getEstado());

        return ubigeoRepository.save(ubigeoInput);
    }

    @Override
    public Ubigeo delete(Long id) {
        Ubigeo ubigeo = ubigeoRepository.findById(id).orElseThrow(() -> new ResourceValidationException(ENTITY, "id", id));
        ubigeo.setEstado("I");
        ubigeoRepository.save(ubigeo);
        return ubigeo;
    }
}
