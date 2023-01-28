package com.app.drop.categoria.service;

import com.app.drop.categoria.domain.model.entity.Categoria;
import com.app.drop.categoria.domain.persistence.CategoriaRepository;
import com.app.drop.categoria.domain.service.CategoriaService;

import com.app.drop.shared.exception.ResourceValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private static final String ENTITY = "Categoria";
    private final CategoriaRepository categoriaRepository;
    private final Validator validator;

    @Override
    public List<Categoria> getAll() {
        return categoriaRepository.findAllActive();
    }

    @Override
    public Categoria getCategoriaById(Long id) {
        return categoriaRepository.findActiveById(id).orElseThrow(() -> new ResourceValidationException(ENTITY, "id", id));
    }

    @Override
    public Categoria create(Categoria categoria) {
        Set<ConstraintViolation<Categoria>> violations = validator.validate(categoria);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria update(Long id, Categoria categoriaInput) {
        Set<ConstraintViolation<Categoria>> violations = validator.validate(categoriaInput);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new ResourceValidationException(ENTITY, "id", id));

        categoria.setNombre(categoriaInput.getNombre());

        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria delete(Long id) {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new ResourceValidationException(ENTITY, "id", id));
        categoriaRepository.delete(categoria);
        return categoria;
    }

    @Override
    public Categoria patch(Long id, char status) {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow();
        categoria.setEstado(status);
        return categoriaRepository.save(categoria);
    }

}