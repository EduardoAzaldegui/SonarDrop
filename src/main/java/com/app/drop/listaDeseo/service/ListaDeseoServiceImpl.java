package com.app.drop.listaDeseo.service;

import com.app.drop.negocio.domain.model.entity.Negocio;
import com.app.drop.negocio.domain.persistence.NegocioRepository;
import com.app.drop.listaDeseo.domain.model.entity.ListaDeseo;
import com.app.drop.listaDeseo.domain.persistence.ListaDeseoRepository;
import com.app.drop.listaDeseo.domain.service.ListaDeseoService;
import com.app.drop.producto.domain.model.entity.Producto;
import com.app.drop.producto.domain.persistence.ProductoRepository;
import com.app.drop.shared.exception.ResourceValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ListaDeseoServiceImpl implements ListaDeseoService {

    private static final String ENTITY = "ListaDeseo";
    private final ListaDeseoRepository listaDeseoRepository;
    private final NegocioRepository negocioRepository;
    private final ProductoRepository productoRepository;
    private final Validator validator;

    @Override
    public List<ListaDeseo> getAll() {
        return listaDeseoRepository.findAllActive();
    }

    @Override
    public ListaDeseo create(ListaDeseo listaDeseo, Long idNegocio, Long idProducto) {
        Set<ConstraintViolation<ListaDeseo>> violations = validator.validate(listaDeseo);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }
        Negocio negocio = negocioRepository.findById(idNegocio).orElseThrow(() -> new ResourceValidationException("Negocio", "id", idNegocio));
        Producto producto = productoRepository.findById(idProducto).orElseThrow(() -> new ResourceValidationException("Producto", "id", idProducto));
        listaDeseo.setNegocio(negocio);
        listaDeseo.setProducto(producto);

        return listaDeseoRepository.save(listaDeseo);
    }

    @Override
    public ListaDeseo delete(Long id) {
        ListaDeseo listaDeseo = listaDeseoRepository.findById(id).orElseThrow(() -> new ResourceValidationException(ENTITY, "id", id));
        listaDeseoRepository.delete(listaDeseo);
        return listaDeseo;
    }

    @Override
    public ListaDeseo patch(Long id, char status) {
        ListaDeseo listaDeseo = listaDeseoRepository.findById(id).orElseThrow();
        listaDeseo.setEstado(status);
        return listaDeseoRepository.save(listaDeseo);
    }

}