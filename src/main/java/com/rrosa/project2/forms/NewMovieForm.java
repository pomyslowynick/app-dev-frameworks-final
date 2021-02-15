package com.rrosa.project2.forms;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class NewMovieForm {
  @NotNull
  @Size(min = 1, max = 100)
  // This regex allows all alphanumeric characters, underscores and spaces
  @Pattern(regexp = "^[A-Za-z0-9_ ]*$")
  private String newMovieName;

  @NotNull
  private int newMovieReleaseYear;

  @NotNull private int directorId;
}
