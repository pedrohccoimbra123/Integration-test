package com.example.demo.domain;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.antlr.v4.runtime.misc.NotNull;

public record RequestProductUpdate(Integer id, @NotBlank String product_name,
@DecimalMin(value = "0.0", message = "O preço do produto deve ser maior que  0") float price,
@Min(value = 0, message = "O estoque do produto deve ser maior ou igual a 0") int stock) {}

