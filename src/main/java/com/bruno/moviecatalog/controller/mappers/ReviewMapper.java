package com.bruno.moviecatalog.controller.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bruno.moviecatalog.controller.dto.ReviewRequestDTO;
import com.bruno.moviecatalog.controller.dto.ReviewResponseDTO;
import com.bruno.moviecatalog.model.Review;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "movie", ignore = true)
	Review toEntity(ReviewRequestDTO reviewRequestDTO);
	
	ReviewResponseDTO toDTO(Review review);

}
