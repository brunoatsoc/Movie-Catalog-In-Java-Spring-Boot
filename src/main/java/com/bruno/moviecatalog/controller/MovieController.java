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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.moviecatalog.controller.dto.MovieAndReviewResponseDTO;
import com.bruno.moviecatalog.controller.dto.MovieRequestDTO;
import com.bruno.moviecatalog.controller.dto.MovieResponseDTO;
import com.bruno.moviecatalog.controller.mappers.MovieMapper;
import com.bruno.moviecatalog.exceptions.ResourceNotFoundException;
import com.bruno.moviecatalog.model.Gender;
import com.bruno.moviecatalog.model.Movie;
import com.bruno.moviecatalog.service.MovieService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController implements GenericController {

	private final MovieService movieService;
	private final MovieMapper movieMapper;

	@PostMapping
	public ResponseEntity<?> createMovie(@RequestBody @Valid MovieRequestDTO movieRequestDTO) {
		Movie movie = movieMapper.toEntity(movieRequestDTO);

		movieService.saveMovie(movie);
		
		var url = generateHeaderLocation(movie.getId());
		
		return ResponseEntity.created(url).build();
	}

	@GetMapping("{id}")
	public ResponseEntity<MovieAndReviewResponseDTO> getMovieById(@PathVariable String id) {
		return movieService.getById(UUID.fromString(id)).map(movie -> {
			var movieDto = movieMapper.toMovieAndReviewResponseDTO(movie);
			return ResponseEntity.ok(movieDto);
		}).orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
	}

	@GetMapping
	public ResponseEntity<List<MovieResponseDTO>> searchMovie(
			@RequestParam(required = false) String title, 
			@RequestParam(value = "release-year", required = false) Integer releaseYear, 
			@RequestParam(required = false) Gender gender) {
		var movies = movieService.searchMovie(title, releaseYear, gender);
		var listDto = movies.stream().map(movieMapper::toDTO).collect(Collectors.toList());

		return ResponseEntity.ok(listDto);
	}

	@PutMapping("{id}")
	public ResponseEntity<?> updateMovie(@PathVariable String id, @RequestBody @Valid MovieRequestDTO movieRequestDTO) {
		return movieService.getById(UUID.fromString(id)).map(movieVar -> {
			movieVar.setTitle(movieRequestDTO.title());
			movieVar.setDescription(movieRequestDTO.description());
			movieVar.setGender(movieRequestDTO.gender());
			movieVar.setReleaseDate(movieRequestDTO.releaseDate());
			
			movieService.updateMovie(movieVar);
			
			return ResponseEntity.noContent().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable String id) {
		return movieService.getById(UUID.fromString(id))
				.map(movie -> {
					movieService.deleteMovie(movie);
					return ResponseEntity.noContent().build();
				}).orElseThrow(() -> new ResourceNotFoundException());
	}

}
