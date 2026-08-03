package com.simpleCRUD.renox.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDTO {


    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min=2,max = 100, message = "El nombre debe tener entre dos o 100 caracteres")
    private String nombre;
    private String apellido;
    @Email(message = "El email debe se valido")
    private String email;
    @Positive(message = "El salario debe ser positivo")
    private Double salario;
    @PastOrPresent(message = "la fecha no puede ser futura")
    private LocalDate fechaIngreso;
}
