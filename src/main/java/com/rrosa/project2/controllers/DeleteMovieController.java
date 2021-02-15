package com.rrosa.project2.controllers;

import com.rrosa.project2.forms.DeleteMovieForm;
import com.rrosa.project2.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class DeleteMovieController {

  @Autowired MovieService movieService;

  @GetMapping("/deletemovie")
  public String deleteMovie(Model model) {
    model.addAttribute("deleteMovieForm", new DeleteMovieForm());
    model.addAttribute("allMovies", movieService.getAllMovies());
    return "deletemovie";
  }

  @PostMapping("/deletemovie")
  public String deleteMoviePost(
      @Valid DeleteMovieForm deleteMovieForm, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("allMovies", movieService.getAllMovies());
      return "redirect:/deletemovie";
    }
    movieService.deleteMovie(deleteMovieForm.getMovieId());
    return "redirect:/movies";
  }
}
