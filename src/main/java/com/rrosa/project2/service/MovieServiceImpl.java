package com.rrosa.project2.service;

import com.rrosa.project2.dao.MovieDao;
import com.rrosa.project2.entities.Director;
import com.rrosa.project2.entities.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {
  @Autowired MovieDao movieDao;

  @Override
  public Movie createNewMovie(String movieName, int releaseYear, Director director) {
    int currentYear = LocalDate.now().getYear();
    if (movieDao.existsByMovieNameAndMovieDirector(movieName, director)) {
      log.error("Movie with such name and for this director is already in the database.");
      return null;
    }

    if (releaseYear > currentYear || releaseYear < 1888) {
      log.error("The release year cannot be less than 1888 or higher than current year: " + currentYear);
      return null;
    }
    return movieDao.save(new Movie(movieName, releaseYear, director));
  }

  @Override
  public Movie getMovieById(int movieId) {
    return movieDao.findByMovieId(movieId);
  }

  @Override
  public List<Movie> getAllMovies() {
    return movieDao.findAllByOrderByReleaseYearAsc();
  }

  @Override
  public boolean deleteMovie(int movieId) {
    long result = movieDao.deleteByMovieId(movieId);
    System.out.println(result);
    return result != 0;
  }

  @Override
  public void changeMovieName(String movieName, int movieId) {
    movieDao.updateMovieName(movieName, movieId);
  }

  @Override
  public boolean isMovieNameInDirectorMovies(String movieName, Director director) {
    return movieDao.existsByMovieNameAndMovieDirector(movieName, director);
  }

  @Override
  public List<Movie> showMoviesForYear(int releaseYear) {
    return movieDao.findAllByReleaseYearEquals(releaseYear);
  }
}
