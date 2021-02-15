package com.rrosa.project2.forms;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class NewDirectorForm {

  @NotNull
  @Size(min = 2, max = 50)
  @Pattern(regexp = "^[A-Za-z0-9]*$")
  private String newDirectorFirstName;

  @Size(min = 2, max = 50)
  @Pattern(regexp = "^[A-Za-z0-9]*$")
  private String newDirectorLastName;
}
