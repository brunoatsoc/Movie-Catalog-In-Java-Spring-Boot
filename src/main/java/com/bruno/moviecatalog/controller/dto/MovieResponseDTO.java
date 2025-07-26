package com.bruno.moviecatalog.controller.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.bruno.moviecatalog.model.Gender;

public record MovieResponseDTO(UUID id, String title, String description, LocalDate releaseDate, Gender gender, Double rating) {

}
