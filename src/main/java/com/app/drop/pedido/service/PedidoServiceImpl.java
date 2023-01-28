package com.app.drop.pedido.service;

import com.app.drop.pedido.domain.model.entity.Pedido;
import com.app.drop.pedido.domain.persistence.PedidoRepository;
import com.app.drop.pedido.domain.service.PedidoService;
import com.app.drop.shared.exception.ResourceValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private static final String ENTITY = "Pedido";
    private final PedidoRepository pedidoRepository;
    private final Validator validator;

    @Override
    public List<Pedido> getAll() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido getPedidoById(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new ResourceValidationException(ENTITY, "id", id));
    }

    @Override
    public Pedido create(Pedido pedido) {
        Set<ConstraintViolation<Pedido>> violations = validator.validate(pedido);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido delete(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new ResourceValidationException(ENTITY, "id", id));
        pedidoRepository.delete(pedido);
        return pedido;
    }

}