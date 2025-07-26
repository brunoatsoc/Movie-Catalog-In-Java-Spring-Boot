package com.bruno.moviecatalog.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_review")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(nullable = true)
	private String comment;

	@Column(nullable = true)
	private Double rating;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "movie_id", referencedColumnName = "id")
	private Movie movie;

	@LastModifiedDate
	@Column(name = "modification_date")
	private LocalDateTime modificationDate;

}
