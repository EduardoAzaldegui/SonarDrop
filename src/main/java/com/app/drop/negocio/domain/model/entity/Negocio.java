package com.app.drop.negocio.domain.model.entity;

import com.app.drop.empleado.domain.model.entity.Employed;
import com.app.drop.listaDeseo.domain.model.entity.ListaDeseo;
import com.app.drop.producto.domain.model.entity.Producto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Max;
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
@Table(name = "NEGOCIO")
public class Negocio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDNEG")
    private Long id;

    @Column(name="TIPNEG", nullable = false, length = 20 )
    private String tipo;

    @Column(name = "NOMNEG", nullable = false,unique = true)
    @NotBlank(message = "El nombre del producto no puede estar vacio")
    @Size(max = 200, message = "El nombre del negocio no puede tener más de 200 caracteres")
    private String nombre;

    @Column(name = "PAGNEG")
    private String paginaWeb;

    @Column(name = "CONTACTO")
    private String contacto;

    @Column(name = "NROCONTACTNEG")
    private String numeroContacto;

    @Column(name = "LOGNEG")
    private String logo;

    @Column(name = "CORRNEG")
    private String email;

    @Column(name = "RUCNEG",unique = true)
    @Min(value = 10000000000L, message = "El RUC debe tener 11 dígitos")
    @Max(value = 99999999999L, message = "El RUC debe tener 11 dígitos")
    private String ruc;

    @Column(name = "ESTNEG")
    private char estado = 'A';

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "negocio", cascade = CascadeType.PERSIST)
    private List<Producto> productos;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idEmp")
    @JsonIdentityReference(alwaysAsId = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "negocio", cascade = CascadeType.PERSIST)
    private List<Employed> empleados;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "negocio", cascade = CascadeType.ALL)
    private List<ListaDeseo> listaDeseos;
}
