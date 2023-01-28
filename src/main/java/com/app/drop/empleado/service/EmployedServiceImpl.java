package com.app.drop.empleado.service;

import com.app.drop.empleado.domain.model.entity.Employed;
import com.app.drop.empleado.domain.persistence.EmployedRepository;
import com.app.drop.empleado.domain.service.EmployedService;
import com.app.drop.negocio.domain.model.entity.Negocio;
import com.app.drop.negocio.domain.persistence.NegocioRepository;
import com.app.drop.shared.exception.ResourceValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class EmployedServiceImpl implements EmployedService {

    private static final String ENTITY = "Employed";
    private final EmployedRepository employedRepository;

    private final NegocioRepository negocioRepository;
    private final Validator validator;

    @Override
    public List<Employed> getAll() {
        return employedRepository.findAllActive();
    }

    @Override
    public Employed getEmployedById(Long id) {
        return employedRepository.findActiveById(id).orElseThrow(() -> new ResourceValidationException(ENTITY, "id", id));
    }

    @Override
    public Employed create(Employed empleado, Long idbusiness) {
        Set<ConstraintViolation<Employed>> violations = validator.validate(empleado);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }
        Negocio negocio = negocioRepository.findById(idbusiness).orElseThrow();
        empleado.setNegocio(negocio);
        return employedRepository.save(empleado);
    }

    @Override
    public Employed update(Long id, Employed empleadoInput) {

        Set<ConstraintViolation<Employed>> violations = validator.validate(empleadoInput);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }
        Employed empleado = employedRepository.findById(id).orElseThrow();
        empleadoInput.setIdEmp(id);
        empleadoInput.setTipEmp(empleado.getTipEmp());
        empleadoInput.setNegocio(empleado.getNegocio());
        return employedRepository.save(empleadoInput);
    }

    @Override
    public Employed delete(Long id) {
        Employed empleado = employedRepository.findById(id).orElseThrow(() -> new ResourceValidationException(ENTITY, "id", id));
        employedRepository.delete(empleado);
        return empleado;
    }

    @Override
    public Employed patch(Long id,char status) {
        Employed empleado = employedRepository.findById(id).orElseThrow();
        empleado.setEstEmp(status);
        return employedRepository.save(empleado);
    }
}
