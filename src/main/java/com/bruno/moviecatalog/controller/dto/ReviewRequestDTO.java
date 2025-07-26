package com.bruno.moviecatalog.controller.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;

public record ReviewRequestDTO(
		@Size(max = 500)
		String comment,
		@DecimalMin(value = "0.0", message = "Movie rating must be greater than or equal to 0.0")
		@DecimalMax(value = "10.0", message = "Movie rating must be less than or equal to 10.0")
		Double rating) {

}
