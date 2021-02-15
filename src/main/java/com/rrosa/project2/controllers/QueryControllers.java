package com.rrosa.project2.controllers;

import com.rrosa.project2.entities.Director;
import com.rrosa.project2.entities.Movie;
import com.rrosa.project2.service.DirectorService;
import com.rrosa.project2.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QueryControllers {

  @Autowired DirectorService directorService;

  @Autowired MovieService movieService;

  @GetMapping("/directors")
  public String showAllDirectors(Model model) {
    List<Director> directors = directorService.getAllDirectors();
    model.addAttribute("directors", directors);
    return "directors";
  }

  @GetMapping("/director/{directorId}")
  public String showDirectorPage(@PathVariable(name = "directorId") int directorId, Model model) {
    Director director = directorService.getDirectorById(directorId);
    model.addAttribute("director", director);
    return "director";
  }

  @GetMapping("/movies/{directorId}")
  public String showDirectorMovies(@PathVariable(name = "directorId") int directorId, Model model) {
    Director director = directorService.getAllMoviesForDirector(directorId);
    List<Movie> directorMovies = director.getDirectorMovies();
    model.addAttribute("movies", directorMovies);
    model.addAttribute("director", director);
    return "movies";
  }

  @GetMapping("/movies")
  public String showAllMovies(Model model) {
    List<Movie> movieList = movieService.getAllMovies();
    model.addAttribute("movies", movieList);
    return "movies";
  }

  @GetMapping("/movie/{movieId}")
  public String showMoviePage(@PathVariable(name = "movieId") int movieId, Model model) {
    Movie movie = movieService.getMovieById(movieId);
    if (movie == null) {
      return "movienotfound";
    }
    model.addAttribute("movie", movie);
    return "movie";
  }

  @GetMapping("/movie/delete/{movieId}")
  public String deleteMovie(@PathVariable(name = "movieId") int movieId, Model model) {
    if (movieService.deleteMovie(movieId)) {
      return "redirect:/movies";
    }
    model.addAttribute("movieId", movieId);
    return "movienotfound";
  }

  @GetMapping("/director/delete/{directorId}")
  public String deleteDirector(@PathVariable(name = "directorId") int directorId, Model model) {
    if (directorService.deleteByDirectorId(directorId)) {
      return "redirect:/directors";
    }
    model.addAttribute("directorId", directorId);
    return "directornotfound";
  }
}
