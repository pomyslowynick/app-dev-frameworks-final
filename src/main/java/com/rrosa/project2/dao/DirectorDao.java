package com.rrosa.project2.dao;

import com.rrosa.project2.entities.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface DirectorDao extends JpaRepository<Director, Integer> {

  Director findByDirectorId(int directorId);

  boolean existsByDirectorFirstNameAndDirectorLastName(
      String directorFirstName, String directorLastName);

  @Query("SELECT d FROM Director d ORDER BY d.directorLastName, d.directorFirstName")
  List<Director> findAllOrderedByNameAndLastName();

  @Modifying
  @Transactional
  long deleteByDirectorId(int directorId);

  @Query(
      "SELECT c FROM Director c LEFT JOIN FETCH c.directorMovies WHERE c.directorId = :directorId")
  Director findDirectorAndMoviesByDirectorId(int directorId);
}
