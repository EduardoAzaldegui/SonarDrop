package com.app.drop.Ubigeo.domain.model.entity;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DBUBIGEO")
public class Ubigeo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDUBI")
    private Long id;

    @Column(name = "DEPAUBI", nullable = false)
    private String departamento;

    @Column(name = "PROVUBI", nullable = false)
    private String provincia;

    @Column(name = "DISTUBI", nullable = false)
    private String distrito;

    @Column(name = "PRECUBI", nullable = false)
    private Double precioDelivery;

    @Column(name = "ESTUBI", nullable = false, columnDefinition = "varchar(20) default 'A'")
    private String estado;

}
