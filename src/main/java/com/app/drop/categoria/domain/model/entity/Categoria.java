package com.app.drop.categoria.domain.model.entity;

import com.app.drop.producto.domain.model.entity.Producto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CATEGORIA")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDCAT")
    private Long id;

    @Column(name = "ESTCAT")
    private char estado = 'A' ;

    @Column(name = "NOMCAT", nullable = false, unique = true)
    @NotBlank(message = "El nombre de la categoria no puede estar vacio")
    @Size(max = 50, message = "El nombre de la categoria no puede tener mas de 50 caracteres")
    private String nombre;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Producto> productos;
}
