package com.rrosa.project2.service;

import com.rrosa.project2.dao.DirectorDao;
import com.rrosa.project2.entities.Director;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DirectorServiceImpl implements DirectorService {
  @Autowired DirectorDao directorDao;

  @Override
  public Director createNewDirector(String firstName, String lastName) {
    if (directorDao.existsByDirectorFirstNameAndDirectorLastName(firstName, lastName)) {
      log.error("Director with such name and last name is already in the database.");
      return null;
    }
    return directorDao.save(new Director(firstName, lastName));
  }

  @Override
  public List<Director> getAllDirectors() {
    return directorDao.findAllOrderedByNameAndLastName();
  }

  @Override
  public Director getAllMoviesForDirector(int directorId) {
    return directorDao.findDirectorAndMoviesByDirectorId(directorId);
  }

  @Override
  public boolean deleteByDirectorId(int directorId) {
    long result = directorDao.deleteByDirectorId(directorId);
    return result != 0;
  }

  @Override
  public Director getDirectorById(int directorId) {
    Director director = directorDao.findByDirectorId(directorId);
    return director;
  }
}
