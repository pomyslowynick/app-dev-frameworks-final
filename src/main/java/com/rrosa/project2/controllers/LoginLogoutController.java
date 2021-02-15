package com.rrosa.project2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginLogoutController {

  @GetMapping("/my_login")
  public String login() {
    return "login";
  }

  @GetMapping("/logout")
  public String logout() {
    return "logout";
  }

  // Added this method from thymeleaf docs https://www.thymeleaf.org/doc/articles/springsecurity.html
  // It displays error messages when user input wrong or non existent username or wrong password
  @GetMapping("/login-error")
  public String loginError(Model model) {
    model.addAttribute("loginError", true);
    return "login.html";
  }

  @GetMapping("/403")
  public String notAuthorised() {
    return "403";
  }
}
