package com.rrosa.project2.service;

import com.rrosa.project2.entities.Director;
import com.rrosa.project2.entities.Movie;

import java.util.List;

public interface MovieService {
  Movie createNewMovie(String movieName, int releaseYear, Director director);

  Movie getMovieById(int movieId);

  List<Movie> getAllMovies();

  boolean deleteMovie(int movieId);

  void changeMovieName(String movieName, int movieId);

  boolean isMovieNameInDirectorMovies(String movieName, Director director);

  List<Movie> showMoviesForYear(int releaseYear);
}
