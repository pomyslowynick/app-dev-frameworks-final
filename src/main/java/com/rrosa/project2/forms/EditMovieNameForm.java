package com.rrosa.project2.forms;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class EditMovieNameForm {

  @NotNull
  @Size(min = 1, max = 100)
  // This regex allows all alphanumeric characters, underscores and spaces
  @Pattern(regexp = "^[A-Za-z0-9_ ]*$")
  private String newMovieName;

  @NotNull private int movieId;
}
