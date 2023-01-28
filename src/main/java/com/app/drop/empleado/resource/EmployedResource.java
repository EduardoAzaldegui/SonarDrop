package com.app.drop.empleado.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class EmployedResource {
    private Long idEmp;
    private String nomEmp;
    private String apeEmp;
    private String usuEmp;
    private String passEmp;
    private Long idNeg;
}
