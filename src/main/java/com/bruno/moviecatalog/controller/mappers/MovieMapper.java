package com.bruno.moviecatalog.controller.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruno.moviecatalog.controller.dto.MovieAndReviewResponseDTO;
import com.bruno.moviecatalog.controller.dto.MovieRequestDTO;
import com.bruno.moviecatalog.controller.dto.MovieResponseDTO;
import com.bruno.moviecatalog.model.Movie;
import com.bruno.moviecatalog.service.ReviewService;

@Mapper(componentModel = "spring")
public abstract class MovieMapper {

	@Autowired
	protected ReviewService reviewService;

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "reviews", ignore = true)
	public abstract Movie toEntity(MovieRequestDTO movieRequestDTO);

	@Mapping(target = "rating", expression = "java(reviewService.getAverageMovieReviews(movie.getId()))")
	public abstract MovieResponseDTO toDTO(Movie movie);

	@Mapping(target = "rating", expression = "java(reviewService.getAverageMovieReviews(movie.getId()))")
	public abstract MovieAndReviewResponseDTO toMovieAndReviewResponseDTO(Movie movie);

}
