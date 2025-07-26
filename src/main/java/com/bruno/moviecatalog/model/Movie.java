package com.bruno.moviecatalog.model;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_movie")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(length = 30, nullable = false)
	private String title;

	@Column(length = 500, nullable = false)
	private String description;

	private LocalDate releaseDate;

	@Enumerated(EnumType.STRING)
	@Column(length = 40, nullable = false)
	private Gender gender;

	@OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
	@JsonIgnore
	Set<Review> reviews;

}
