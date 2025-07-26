package com.bruno.moviecatalog.controller.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReviewResponseDTO(UUID id, String comment, Double rating, LocalDateTime modificationDate) {

}
