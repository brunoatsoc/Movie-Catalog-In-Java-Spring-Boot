package com.bruno.moviecatalog.controller.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.bruno.moviecatalog.model.Gender;

public record MovieAndReviewResponseDTO(UUID id, String title, String description, LocalDate releaseDate, Gender gender, Double rating, List<ReviewResponseDTO> reviews) {

}
