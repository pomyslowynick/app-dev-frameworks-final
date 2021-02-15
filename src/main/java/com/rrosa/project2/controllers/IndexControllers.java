package com.rrosa.project2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexControllers {

  @GetMapping("/")
  public String loadIndexHome(Model model) {
    return "index";
  }

  @GetMapping("/index")
  public String loadIndexPage(Model model) {
    return "index";
  }
}
