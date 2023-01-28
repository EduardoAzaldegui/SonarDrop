package com.app.drop.Ubigeo.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateUbigeoResource {
    private String departamento;
    private String provincia;
    private String distrito;
    private Double precioDelivery;

}
