package com.bruno.moviecatalog.repository.spec;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import com.bruno.moviecatalog.model.Gender;
import com.bruno.moviecatalog.model.Movie;

public class MovieSpecification {

	public static Specification<Movie> titleLike(String title) {
		return (root, query, cb) -> {
			if (ObjectUtils.isEmpty(title)) {
				return null;
			}
			
			return cb.like(cb.upper(root.get("title")), "%" + title.toUpperCase() + "%");
		};
	}

	public static Specification<Movie> releaseYearBetween(Integer releaseYear) {
		return (root, query, cb) -> {
			if (ObjectUtils.isEmpty(releaseYear)) {
				return null;
			}
			
			LocalDate startDate = LocalDate.of(releaseYear, 1, 1);
			LocalDate endDate = LocalDate.of(releaseYear, 12, 31);
			
			return cb.between(root.get("releaseDate"), startDate, endDate);
		};
	}

	public static Specification<Movie> genderEqual(Gender gender) {
		return (root, query, cb) -> {
			if (ObjectUtils.isEmpty(gender)) {
				return null;
			}
			
			return cb.equal(root.get("gender"), gender);
		};
	}

}
