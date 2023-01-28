package com.app.drop.empleado.domain.model.entity;

import com.app.drop.negocio.domain.model.entity.Negocio;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

@Data
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMPLEADO")
public class Employed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDEMP")
    private Long idEmp;

    @Column(name = "TIPEMP")
    private String tipEmp;

    @Column(name = "NOMEMP")
    private String nomEmp;

    @Column(name = "APEMP")
    private String apeEmp;

    @Column(name = "USUEMP")
    private String usuEmp;

    @Column(name = "PASSEMP")
    private String passEmp;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne
    @JoinColumn(name = "IDNEG", referencedColumnName = "IDNEG")
    private Negocio negocio;

    @Column(name = "ESTEMP")
    private char estEmp = 'A';
}
