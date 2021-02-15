package com.rrosa.project2.controllers;

import com.rrosa.project2.entities.MyUser;
import com.rrosa.project2.forms.RegistrationForm;
import com.rrosa.project2.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class RegistrationController {
  @Autowired
  MyUserService myUserService;

  @Autowired
  PasswordEncoder passwordEncoder;
  @GetMapping("/register")
  public String registerUser(Model model) {
    model.addAttribute("registrationForm", new RegistrationForm());
    return "register";

  }

  @PostMapping("/register")
  public String registerUserPost(@Valid RegistrationForm registrationForm,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes,
                                 Model model) {
    if (bindingResult.hasErrors()) {
      return "register";
    }
    MyUser myUser = new MyUser(registrationForm.getUsername(), passwordEncoder.encode(registrationForm.getPassword()), "USER");
    MyUser createdUser = myUserService.createNewUser(myUser);
    if (createdUser == null) {
      redirectAttributes.addFlashAttribute("duplicateEmail", registrationForm.getUsername());
      return "redirect:register";
    }
    return "redirect:my_login";
  }
}
