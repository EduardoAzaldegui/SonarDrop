package com.app.drop.pedido.domain.model.entity;

import com.app.drop.Ubigeo.domain.model.entity.Ubigeo;
import com.app.drop.empleado.domain.model.entity.Employed;
import com.app.drop.negocio.domain.model.entity.Negocio;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PEDIDO")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPED")
    private Long id;

    @Column(name = "NOMCLIPED", nullable = false)
    @NotBlank(message = "El nombre de la categoria no puede estar vacio")
    @Size(max = 120, message = "El nombre de la categoria no puede tener mas de 120 caracteres")
    private String nombre;

    @Column(name ="DIRECLIPED", nullable = false)
    @NotBlank(message = "La direccion del cliente no puede estar vacia")
    @Size(max = 200, message = "La direccion del cliente no puede tener mas de 200 caracteres")
    private String direccion;

    @Column(name = "REFCLIPED", nullable = false)
    @NotBlank(message = "La referencia del cliente no puede estar vacia")
    @Size(max = 200, message = "La referencia del cliente no puede tener mas de 200 caracteres")
    private String referencia;

    @Column(name = "TELCLIPED", nullable = false)
    @NotBlank(message = "El telefono del cliente no puede estar vacio")
    @Size(max = 12, message = "El telefono del cliente no puede tener mas de 12 caracteres")
    private String telefono;

    // estado del pedido
    @Column(name = "ESTPEDIDO")
    @NotBlank(message = "El estado del pedido no puede estar vacio")
    @Size(max = 24, message = "El estado del pedido no puede tener mas de 24 caracteres")
    private String estadoPedido;

    @Column(name = "ESTASIGPEDI")
    @NotBlank(message = "El estado de asignacion del pedido no puede estar vacio")
    @Size(max = 20, message = "El estado de asignacion del pedido no puede tener mas de 20 caracteres")
    private String estadoAsignacion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHPED", nullable = false, updatable = false)
    @CreatedDate
    private Date fechaPedido;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHREGPED", nullable = false, updatable = false)
    @CreatedDate
    private Date fechaRegistroPedido;

    @Column(name = "OBSPED")
    @Size(max = 200, message = "Las observaciones del pedido no pueden tener mas de 200 caracteres")
    private String observaciones;

    @Column(name = "ESTPAGNEG")
    @NotBlank(message = "El estado de pago del pedido no puede estar vacio")
    @Size(max = 20, message = "El estado de pago del pedido no puede tener mas de 20 caracteres")
    private String estadoPago;

    @Column(name = "OBSENTREGPED")
    @Size(max = 20, message = "Las observaciones de la entrega del pedido no pueden tener mas de 20 caracteres")
    private String observacionesEntrega;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne
    @JoinColumn(name = "IDUBI", referencedColumnName = "IDUBI")
    private Ubigeo ubigeo;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne
    @JoinColumn(name = "IDEMP", referencedColumnName = "IDEMP")
    private Employed empleado;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne
    @JoinColumn(name = "IDNEG", referencedColumnName = "IDNEG")
    private Negocio negocio;

//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//    @JsonIdentityReference(alwaysAsId = true)
//    @LazyCollection(LazyCollectionOption.FALSE)
//    @OneToMany(mappedBy = "detallePedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<DetallePedido> detallePedidos;
}
