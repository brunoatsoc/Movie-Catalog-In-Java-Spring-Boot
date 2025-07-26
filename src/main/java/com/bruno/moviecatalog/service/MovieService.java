package com.bruno.moviecatalog.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bruno.moviecatalog.exceptions.UnregisteredEntityException;
import com.bruno.moviecatalog.model.Gender;
import com.bruno.moviecatalog.model.Movie;
import com.bruno.moviecatalog.model.Review;
import com.bruno.moviecatalog.repository.MovieRepository;
import com.bruno.moviecatalog.repository.ReviewRepository;
import com.bruno.moviecatalog.repository.spec.MovieSpecification;
import com.bruno.moviecatalog.validators.MovieValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {

	private final MovieRepository movieRepository;
	private final ReviewRepository reviewRepository;
	private final MovieValidator movieValidator;

	public Movie saveMovie(Movie movie) {
		movieValidator.validate(movie);

		return movieRepository.save(movie);
	}

	public List<Movie> searchMovie(String title, Integer releaseYear, Gender gender) {
		return movieRepository.findAll(MovieSpecification.titleLike(title)
				.and(MovieSpecification.releaseYearBetween(releaseYear))
				.and(MovieSpecification.genderEqual(gender)));
	}

	public Optional<Movie> getById(UUID id) {
		return movieRepository.findById(id).map(movie -> {
			List<Review> reviews = reviewRepository.findByMovieIdOrderByModificationDateDesc(id);
			
			movie.setReviews(new LinkedHashSet<Review>(reviews));
			
			return movie;
		});
	}

	public void updateMovie(Movie movie) {
		if (movie.getId() == null) {
			throw new UnregisteredEntityException("The movie must be registered to be updated");
		}

		movieValidator.validate(movie);
		movieRepository.save(movie);
	}

	public void deleteMovie(Movie movie) {
		movieRepository.delete(movie);
	}

}
