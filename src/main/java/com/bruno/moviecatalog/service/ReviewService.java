package com.bruno.moviecatalog.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bruno.moviecatalog.exceptions.ResourceNotFoundException;
import com.bruno.moviecatalog.exceptions.UnregisteredEntityException;
import com.bruno.moviecatalog.model.Review;
import com.bruno.moviecatalog.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {

	private final ReviewRepository reviewRepository;
	private final MovieService movieService;

	public Review saveReview(UUID id, Review review) {
		return movieService.getById(id).map(mv -> {
			review.setMovie(mv);
			
			return reviewRepository.save(review);
		}).orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
	}

	public Optional<Review> getById(UUID movieId, UUID reviewId) {
		// return reviewRepository.findById(id);
		return reviewRepository.findByMovieIdAndId(movieId, reviewId);
	}

	public List<Review> getMovieReviews(UUID movieId) {
		return movieService.getById(movieId).map(mv -> {
			return reviewRepository.findByMovieIdOrderByModificationDateDesc(mv.getId());
		}).orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
	}

	public Double getAverageMovieReviews(UUID movieId) {
		Double average = reviewRepository.findAverageMovieReviews(movieId);
		
		return average != null ? BigDecimal.valueOf(average).setScale(2, RoundingMode.HALF_UP).doubleValue() : 0.0;
	}

	public void updateReview(Review review) {
		if (review.getId() == null) {
			throw new UnregisteredEntityException("The review must be registered to be updated");
		}
	
		reviewRepository.save(review);
	}

	public void deletReview(Review review) {
		reviewRepository.delete(review);
	}

}
