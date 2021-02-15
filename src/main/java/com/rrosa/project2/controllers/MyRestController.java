package com.rrosa.project2.controllers;

import com.rrosa.project2.entities.Director;
import com.rrosa.project2.entities.Movie;
import com.rrosa.project2.service.DirectorService;
import com.rrosa.project2.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("myapi")
public class MyRestController {

  @Autowired MovieService movieService;
  @Autowired DirectorService directorService;

  @GetMapping("/movies")
  List<Movie> getMovies() {
    return movieService.getAllMovies();
  }

  @GetMapping("/directors")
  List<Director> getDirectors() {
    return directorService.getAllDirectors();
  }

  // I was following the lectures while working on the project and as a result I have included API
  // methods which did not end up actually using
  @GetMapping("/movie/{movieId}")
  ResponseEntity<Movie> getMovie(@PathVariable(name = "movieId") int movieId) {
    Movie movie = movieService.getMovieById(movieId);
    if (movie == null) {
      return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Movie>(movie, HttpStatus.FOUND);
  }

  @GetMapping("/moviesbydirector/{directorId}")
  ResponseEntity<List<Movie>> getMoviesForDirectorId(
      @PathVariable(name = "directorId") int directorId) {
    Director director = directorService.getAllMoviesForDirector(directorId);
    if (director == null) {
      return new ResponseEntity<List<Movie>>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<List<Movie>>(director.getDirectorMovies(), HttpStatus.FOUND);
  }

  @GetMapping("/moviesreleasedinyear/{releaseYear}")
  ResponseEntity<List<Movie>> getMoviesReleasedInYear(
      @PathVariable(name = "releaseYear") int releaseYear) {
    List<Movie> movieList = movieService.showMoviesForYear(releaseYear);
    if (movieList == null) {
      return new ResponseEntity<List<Movie>>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<List<Movie>>(movieList, HttpStatus.FOUND);
  }

  @PostMapping("/movie")
  ResponseEntity<Movie> createNewMovie(
      @RequestBody String movieName, @RequestBody int releaseYear, @RequestBody int directorId) {
    Director director = directorService.getDirectorById(directorId);
    Movie movie = movieService.createNewMovie(movieName, releaseYear, director);
    if (movie == null) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    return new ResponseEntity<>(movie, HttpStatus.CREATED);
  }

  @DeleteMapping("/director/{directorId}")
  ResponseEntity<Director> deleteDirector(@PathVariable(name = "directorId") int directorId) {
    boolean status = directorService.deleteByDirectorId(directorId);
    if (status) {
      return new ResponseEntity<Director>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<Director>(HttpStatus.NOT_FOUND);
  }
}
