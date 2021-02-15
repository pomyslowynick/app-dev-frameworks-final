package com.rrosa.project2.controllers;

import com.rrosa.project2.entities.Director;
import com.rrosa.project2.forms.NewDirectorForm;
import com.rrosa.project2.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class NewDirectorController {

  @Autowired DirectorService directorService;

  @GetMapping("/newdirector")
  public String addNewDirector(Model model) {
    model.addAttribute("newDirectorForm", new NewDirectorForm());
    return "newdirector";
  }

  @PostMapping("/newdirector")
  public String addNewDirectorPost(
      @Valid NewDirectorForm newDirectorForm,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      return "newdirector";
    }
    Director director =
        directorService.createNewDirector(
            newDirectorForm.getNewDirectorFirstName(), newDirectorForm.getNewDirectorLastName());
    if (director == null) {
      String directorFullName =
          newDirectorForm.getNewDirectorFirstName()
              + " "
              + newDirectorForm.getNewDirectorLastName();
      redirectAttributes.addFlashAttribute("duplicateDirectorName", directorFullName);
      return "redirect:newdirector";
    }
    return "redirect:director/" + director.getDirectorId();
  }
}
