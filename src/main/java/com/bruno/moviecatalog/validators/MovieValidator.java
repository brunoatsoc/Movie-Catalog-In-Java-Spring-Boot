package com.bruno.moviecatalog.validators;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.bruno.moviecatalog.exceptions.DuplicateEntityException;
import com.bruno.moviecatalog.model.Movie;
import com.bruno.moviecatalog.repository.MovieRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MovieValidator {

	private final MovieRepository movieRepository;

	public void validate(Movie movie) {
		if (existsRegisteredMovie(movie)) {
			throw new DuplicateEntityException("It is not possible to register two identical films");
		}
	}

	private boolean existsRegisteredMovie(Movie movie) {
		Optional<Movie> foundMovie = movieRepository.findByTitleAndReleaseDateAndGenderAndDescription(
				movie.getTitle(), 
				movie.getReleaseDate(), 
				movie.getGender(), 
				movie.getDescription());
		
		if (movie.getId() == null) {
			return foundMovie.isPresent();
		}
		
		return foundMovie.isPresent() && !movie.getId().equals(foundMovie.get().getId());
	}

}
