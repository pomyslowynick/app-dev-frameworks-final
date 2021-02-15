package com.rrosa.project2.dao;

import com.rrosa.project2.entities.Director;
import com.rrosa.project2.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface MovieDao extends JpaRepository<Movie, Integer> {
  Movie findByMovieId(int movieId);

  boolean existsByMovieNameAndMovieDirector(String movieName, Director director);

  List<Movie> findAllByOrderByReleaseYearAsc();

  List<Movie> findAllByReleaseYearEquals(int releaseYear);

  @Modifying
  @Transactional
  long deleteByMovieId(int movieId);

  @Modifying
  @Transactional
  @Query("UPDATE Movie m SET m.movieName = :movieName WHERE m.movieId = :movieId")
  void updateMovieName(@Param("movieName") String movieName, @Param("movieId") int movieId);
}
