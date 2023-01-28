package com.app.drop.producto.domain.model.entity;

import com.app.drop.categoria.domain.model.entity.Categoria;
import com.app.drop.listaDeseo.domain.model.entity.ListaDeseo;
import com.app.drop.negocio.domain.model.entity.Negocio;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCTO")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPRO")
    private Long id;

    @Column(name = "NOMPRO", nullable = false)
    @NotBlank(message = "El nombre del producto no puede estar vacio")
    @Size(max = 200, message = "El nombre del producto no puede tener más de 200 caracteres")
    private String nombre;

    @Column(name = "STOCK", nullable = false)
    @Min(value = 0, message = "El stock del producto no puede ser negativo")
    private Integer stock = 0;

    @Column(name = "URLIMG", nullable = false)
    private String imagen;

    @Column(name = "PRECVENT", nullable = false,precision = 2)
    @Min(value = 0, message = "El precio de venta del producto no puede ser negativo")
    private Double precioVenta = 0.00;

    @Column(name = "ESTPRO")
    private char estado = 'A';

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne
    @JoinColumn(name = "IDCAT", referencedColumnName = "IDCAT")
    private Categoria categoria;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne
    @JoinColumn(name = "IDNEG", referencedColumnName = "IDNEG")
    private Negocio negocio;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "producto", cascade = CascadeType.PERSIST)
    private List<ListaDeseo> listaDeseos;

}
