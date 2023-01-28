package com.app.drop.rol.service;

import com.app.drop.rol.domain.model.entity.Rol;
import com.app.drop.rol.domain.persistence.RolRepository;
import com.app.drop.rol.domain.service.RolService;
import com.app.drop.shared.exception.ResourceValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class RolServiceImpl implements RolService {
    private static final String ENTITY = "Rol";
    private final RolRepository rolRepository;
    private final Validator validator;

    @Override
    public List<Rol> getAll() {
        return rolRepository.findAllActive();
    }

    @Override
    public Rol getRolById(Long id) {
        return rolRepository.findById(id).orElseThrow(() -> new ResourceValidationException(ENTITY, "id", id));
    }

    @Override
    public Rol create(Rol rol) {
        Set<ConstraintViolation<Rol>> violations = validator.validate(rol);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        return rolRepository.save(rol);
    }

    @Override
    public Rol update(Long id, Rol rolInput) {
        Set<ConstraintViolation<Rol>> violations = validator.validate(rolInput);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        Rol rol = rolRepository.findById(id).orElseThrow(() -> new ResourceValidationException(ENTITY, "id", id));
        rol.setNombre(rolInput.getNombre());

        return rolRepository.save(rol);
    }

    @Override
    public Rol delete(Long id) {
        Rol rol = rolRepository.findById(id).orElseThrow(() -> new ResourceValidationException(ENTITY, "id", id));
        rol.setEstado('I');
        rolRepository.save(rol);
        return rol;
    }

}
