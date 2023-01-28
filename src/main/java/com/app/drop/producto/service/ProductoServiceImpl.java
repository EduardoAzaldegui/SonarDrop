package com.app.drop.producto.service;

import com.app.drop.categoria.domain.model.entity.Categoria;
import com.app.drop.categoria.domain.persistence.CategoriaRepository;
import com.app.drop.negocio.domain.model.entity.Negocio;
import com.app.drop.negocio.domain.persistence.NegocioRepository;
import com.app.drop.producto.domain.model.entity.Producto;
import com.app.drop.producto.domain.persistence.ProductoRepository;
import com.app.drop.producto.domain.service.ProductoService;
import com.app.drop.shared.exception.ResourceValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private static final String ENTITY = "Product";
    private final ProductoRepository productoRepository;
    private final NegocioRepository negocioRepository;

    private final CategoriaRepository categoriaRepository;
    private final Validator validator;

    @Override
    public List<Producto> getAll() {
        return productoRepository.findAllActive();
    }

    @Override
    public Producto getProductById(Long id) {
        return productoRepository.findActiveById(id).orElseThrow(() -> new ResourceValidationException(ENTITY, "id", id));
    }

    @Override
    public Producto create(Producto producto, Long idbusiness, Long idCategories) {
        Set<ConstraintViolation<Producto>> violations = validator.validate(producto);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }
        Negocio negocio = negocioRepository.findById(idbusiness).orElseThrow();
        Categoria categoria = categoriaRepository.findById(idCategories).orElseThrow();
        producto.setNegocio(negocio);
        producto.setCategoria(categoria);

        return productoRepository.save(producto);
    }

    @Override
    public Producto update(Long id, Producto productoInput, Long idCategories) {
        Set<ConstraintViolation<Producto>> violations = validator.validate(productoInput);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        Producto producto = productoRepository.findById(id).orElseThrow(() -> new ResourceValidationException(ENTITY, "id", id));
        Categoria categoria = categoriaRepository.findById(idCategories).orElseThrow(() -> new ResourceValidationException("Categoria", "id", id));

        productoInput.setId(producto.getId());
        productoInput.setCategoria(categoria);
        productoInput.setNegocio(producto.getNegocio());

        return productoRepository.save(productoInput);
    }

    @Override
    public Producto delete(Long id) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new ResourceValidationException(ENTITY, "id", id));
        productoRepository.delete(producto);
        return producto;
    }

    public Producto patch(Long id, char status) {
        Producto producto = productoRepository.findById(id).orElseThrow();
        producto.setEstado(status);
        return productoRepository.save(producto);
    }

}