package com.rrosa.project2.controllers;

import com.rrosa.project2.forms.DeleteDirectorForm;
import com.rrosa.project2.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class DeleteDirectorController {

  @Autowired DirectorService directorService;

  @GetMapping("/deletedirector")
  public String deleteDirector(Model model) {
    model.addAttribute("deleteDirectorForm", new DeleteDirectorForm());
    model.addAttribute("allDirectors", directorService.getAllDirectors());
    return "deletedirector";
  }

  @PostMapping("/deletedirector")
  public String deleteDirectorPost(
      @Valid DeleteDirectorForm deleteDirectorForm, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("allMovies", directorService.getAllDirectors());
      return "redirect:/deletedirector";
    }
    directorService.deleteByDirectorId(deleteDirectorForm.getDirectorId());
    return "redirect:/directors";
  }
}
