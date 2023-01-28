package com.app.drop.negocio.service;

import com.app.drop.negocio.domain.model.entity.Negocio;
import com.app.drop.negocio.domain.persistence.NegocioRepository;
import com.app.drop.negocio.domain.service.NegocioService;
import com.app.drop.shared.exception.ResourceValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class NegocioServiceImpl implements NegocioService {

    private static final String ENTITY = "Negocio";
    private final NegocioRepository negocioRepository;
    private final Validator validator;

    @Override
    public List<Negocio> getAll() {
        return negocioRepository.findAllActive();
    }

    @Override
    public Negocio getNegocioById(Long id) {
        return negocioRepository.findActiveById(id).orElseThrow(() -> new ResourceValidationException(ENTITY, "id", id));
    }

    @Override
    public Negocio create(Negocio negocio) {
        return negocioRepository.save(negocio);
    }

    @Override
    public Negocio update(Long id, Negocio negocioInput) {
        Set<ConstraintViolation<Negocio>> violations = validator.validate(negocioInput);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        Negocio negocio = negocioRepository.findById(id).orElseThrow(() -> new ResourceValidationException(ENTITY, "id", id));
        negocioInput.setId(negocio.getId());
        negocioInput.setEmpleados(negocio.getEmpleados());
        negocioInput.setProductos(negocio.getProductos());
        return negocioRepository.save(negocioInput);
    }

    @Override
    public Negocio delete(Long id) {
        Negocio negocio = negocioRepository.findById(id).orElseThrow(() -> new ResourceValidationException(ENTITY, "id", id));
        negocioRepository.delete(negocio);
        return negocio;
    }

    @Override
    public Negocio patch(Long id, char status) {
        Negocio negocio = negocioRepository.findById(id).orElseThrow();
        negocio.setEstado(status);
        return negocioRepository.save(negocio);
    }


}