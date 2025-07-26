package com.bruno.moviecatalog.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.moviecatalog.controller.dto.ReviewRequestDTO;
import com.bruno.moviecatalog.controller.dto.ReviewResponseDTO;
import com.bruno.moviecatalog.controller.mappers.ReviewMapper;
import com.bruno.moviecatalog.exceptions.ResourceNotFoundException;
import com.bruno.moviecatalog.model.Review;
import com.bruno.moviecatalog.service.ReviewService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("movies/{movieId}/reviews")
@RequiredArgsConstructor
public class ReviewController implements GenericController {

	private final ReviewService reviewService;
	private final ReviewMapper reviewMapper;

	@PostMapping
	public ResponseEntity<?> postReview(@PathVariable String movieId, @RequestBody @Valid ReviewRequestDTO reviewRequestDTO) {
		Review review = reviewMapper.toEntity(reviewRequestDTO);
		
		reviewService.saveReview(UUID.fromString(movieId), review);
		
		var url = generateHeaderLocation(review.getId());
		
		return ResponseEntity.created(url).build();
	}

	@GetMapping("{reviewId}")
	public ResponseEntity<?> getReviewById(@PathVariable String movieId, @PathVariable String reviewId) {
		return reviewService.getById(UUID.fromString(movieId), UUID.fromString(reviewId)).map(review -> {
			var reviewDTO = reviewMapper.toDTO(review);
			
			return ResponseEntity.ok(reviewDTO);
		}).orElseThrow(() -> new ResourceNotFoundException("Review not found"));
	}

	@GetMapping("movie-reviews")
	public ResponseEntity<List<ReviewResponseDTO>> getMovieReviews(@PathVariable String movieId) {
		var list = reviewService.getMovieReviews(UUID.fromString(movieId)).stream().map(reviewMapper::toDTO).collect(Collectors.toList());
		
		return ResponseEntity.ok(list);
	}

	@PutMapping("{reviewId}")
	public ResponseEntity<?> updateReview(@PathVariable String movieId, @PathVariable String reviewId, @RequestBody @Valid ReviewRequestDTO reviewRequestDTO) {
		return reviewService.getById(UUID.fromString(movieId), UUID.fromString(reviewId)).map(review -> {
			review.setComment(reviewRequestDTO.comment());
			review.setRating(reviewRequestDTO.rating());
			
			reviewService.updateReview(review);
			
			return ResponseEntity.noContent().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Review not found"));
	}

	@DeleteMapping("{reviewId}")
	public ResponseEntity<?> deleteEeview(@PathVariable String movieId, @PathVariable String reviewId) {
		return reviewService.getById(UUID.fromString(movieId), UUID.fromString(reviewId)).map(review -> {
			reviewService.deletReview(review);
			
			return ResponseEntity.noContent().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Review not found"));
	}

}
