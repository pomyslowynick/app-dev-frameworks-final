package com.rrosa.project2.service;

import com.rrosa.project2.entities.Director;

import java.util.List;

public interface DirectorService {
  Director createNewDirector(String firstName, String lastName);

  List<Director> getAllDirectors();

  Director getAllMoviesForDirector(int directorId);

  boolean deleteByDirectorId(int directorId);

  Director getDirectorById(int directorId);
}
