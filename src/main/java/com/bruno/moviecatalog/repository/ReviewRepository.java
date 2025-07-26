package com.bruno.moviecatalog.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bruno.moviecatalog.model.Movie;
import com.bruno.moviecatalog.model.Review;

public interface ReviewRepository extends JpaRepository<Review, UUID>, JpaSpecificationExecutor<Review> {

	Optional<Review> findByMovieIdAndId(UUID movieId, UUID reviewId);

	List<Review> findByMovie(Movie movie);

	@Query("SELECT AVG(rating) FROM Review WHERE movie.id = :movieId")
	Double findAverageMovieReviews(@Param("movieId") UUID movieId);

	List<Review> findByMovieIdOrderByModificationDateDesc(UUID movieId);

}
