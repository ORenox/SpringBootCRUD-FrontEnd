package com.simpleCRUD.renox.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private Double salario;
    private LocalDate fechaIngreso;
}
