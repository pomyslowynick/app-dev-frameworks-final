package com.rrosa.project2.controllers;

import com.rrosa.project2.entities.Director;
import com.rrosa.project2.entities.Movie;
import com.rrosa.project2.forms.NewMovieForm;
import com.rrosa.project2.service.DirectorService;
import com.rrosa.project2.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@Slf4j
public class NewMovieController {

  @Autowired MovieService movieService;

  @Autowired DirectorService directorService;

  @GetMapping("/newmovie")
  public String addNewMovie(Model model) {
    model.addAttribute("newMovieForm", new NewMovieForm());
    model.addAttribute("directors", directorService.getAllDirectors());
    return "newmovie";
  }

  @PostMapping("/newmovie")
  public String addNewMoviePost(
      @Valid NewMovieForm newMovieForm,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes,
      Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("directors", directorService.getAllDirectors());
      return "newmovie";
    }
    int currentYear = LocalDate.now().getYear();
    int inputtedYear = newMovieForm.getNewMovieReleaseYear();
    if (inputtedYear > currentYear || inputtedYear < 1888) {
      log.error(
          "The release year cannot be less than 1888 or higher than current year: " + currentYear);
      if (inputtedYear == 0)
        inputtedYear += 1; // hacky way of solving an edge case, th:if treats 0 as false
      redirectAttributes.addFlashAttribute("incorrectYear", inputtedYear);
      redirectAttributes.addFlashAttribute("currentYear", currentYear);
      return "redirect:newmovie";
    }
    Director director = directorService.getDirectorById(newMovieForm.getDirectorId());
    Movie movie =
        movieService.createNewMovie(newMovieForm.getNewMovieName(), inputtedYear, director);

    if (movie == null) {
      String directorFullName =
          director.getDirectorFirstName() + " " + director.getDirectorLastName();
      redirectAttributes.addFlashAttribute("duplicateMovieName", newMovieForm.getNewMovieName());
      redirectAttributes.addFlashAttribute("duplicateDirector", directorFullName);
      return "redirect:newmovie";
    }
    return "redirect:movie/" + movie.getMovieId();
  }
}
