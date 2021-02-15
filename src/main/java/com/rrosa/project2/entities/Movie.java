package com.rrosa.project2.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Movie {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int movieId;

  @Column(nullable = false)
  private String movieName;

  @Column() private int releaseYear;

  @ManyToOne
  @JoinColumn(name = "director_id", nullable = false)
  private Director movieDirector;

  public Movie(String movieName, int releaseYear, Director director) {
    this.movieName = movieName;
    this.releaseYear = releaseYear;
    this.movieDirector = director;
  }
}
