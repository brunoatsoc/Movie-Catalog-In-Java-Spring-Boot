package com.bruno.moviecatalog.controller.dto;

import java.time.LocalDate;

import com.bruno.moviecatalog.model.Gender;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MovieRequestDTO(
		@NotBlank(message = "The movie needs to have a Title")
		@Size(max = 100)
		String title, 
		@NotBlank(message = "The movie needs to have a Description")
		@Size(max = 500, message = "The description must have a maximum of 500 characters")
		String description, 
		@NotNull(message = "The movie needs to have a Release Date")
		LocalDate releaseDate, 
		@NotNull(message = "The movie needs to have a Gender")
		Gender gender) {

}
