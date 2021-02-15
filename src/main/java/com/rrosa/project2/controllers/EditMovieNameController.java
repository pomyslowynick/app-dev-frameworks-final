package com.rrosa.project2.controllers;

import com.rrosa.project2.entities.Director;
import com.rrosa.project2.entities.Movie;
import com.rrosa.project2.forms.EditMovieNameForm;
import com.rrosa.project2.service.DirectorService;
import com.rrosa.project2.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class EditMovieNameController {

  @Autowired MovieService movieService;

  @Autowired DirectorService directorService;

  @GetMapping("/updatemoviename")
  public String editMovieName(Model model) {
    model.addAttribute("editMovieNameForm", new EditMovieNameForm());
    model.addAttribute("allMovies", movieService.getAllMovies());
    return "updatemoviename";
  }

  @PostMapping("/updatemoviename")
  public String editMovieNamePost(
      @Valid EditMovieNameForm editMovieNameForm,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes,
      Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("allMovies", movieService.getAllMovies());
      return "updatemoviename";
    }

    Movie movie = movieService.getMovieById(editMovieNameForm.getMovieId());
    Director director = movie.getMovieDirector();
    boolean isMoviePresent =
        movieService.isMovieNameInDirectorMovies(editMovieNameForm.getNewMovieName(), director);
    if (isMoviePresent) {
      String directorFullName =
          director.getDirectorFirstName() + " " + director.getDirectorLastName();
      redirectAttributes.addFlashAttribute(
          "duplicateMovieName", editMovieNameForm.getNewMovieName());
      redirectAttributes.addFlashAttribute("duplicateDirector", directorFullName);
      return "redirect:updatemoviename";
    }
    movieService.changeMovieName(
        editMovieNameForm.getNewMovieName(), editMovieNameForm.getMovieId());
    return "redirect:movie/" + movie.getMovieId();
  }
}
