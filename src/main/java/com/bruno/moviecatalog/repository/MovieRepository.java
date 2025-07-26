package com.bruno.moviecatalog.repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bruno.moviecatalog.model.Gender;
import com.bruno.moviecatalog.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, UUID>, JpaSpecificationExecutor<Movie> {

	Optional<Movie> findByTitleAndReleaseDateAndGenderAndDescription(String title, LocalDate releaseDate, Gender gender, String description);

}
