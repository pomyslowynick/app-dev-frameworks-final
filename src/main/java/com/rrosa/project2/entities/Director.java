package com.rrosa.project2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Director {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int directorId;

  @Column(nullable = false)
  private String directorFirstName;

  @Column() private String directorLastName;

  @JsonIgnore
  @OneToMany(mappedBy = "movieDirector", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private List<Movie> directorMovies;

  public Director(String firstName, String lastName) {
    this.directorFirstName = firstName;
    this.directorLastName = lastName;
  }
}
