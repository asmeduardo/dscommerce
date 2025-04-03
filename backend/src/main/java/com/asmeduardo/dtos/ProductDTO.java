package com.example.dscommerce.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductDTO (

    Long id,

    @Size(min = 2, max = 80, message = "O nome precisa ter de 3 a 80 caracteres")
    @NotBlank(message = "Campo requerido")
    String name,

    @Size(min = 10, message = "Descrição precisa ter no mínimo 10 caracteres")
    @NotBlank(message = "Campo requerido")
    String description,

    @Positive(message = "O preço precisa ser positivo")
    Double price, String imgUrl) {
}
