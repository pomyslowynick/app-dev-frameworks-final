package com.rrosa.project2.forms;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DeleteMovieForm {

  @NotNull private int movieId;
}
