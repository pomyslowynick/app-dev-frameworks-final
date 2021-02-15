package com.rrosa.project2.forms;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class RegistrationForm {
  @Size(min = 3, max = 100)
  @Email
  private String username;

  @Size(min = 6, max = 100)
  private String password;

}
