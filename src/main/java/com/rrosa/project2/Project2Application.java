package com.rrosa.project2;


import com.rrosa.project2.entities.Director;
import com.rrosa.project2.entities.MyUser;
import com.rrosa.project2.service.DirectorService;
import com.rrosa.project2.service.MovieService;
import com.rrosa.project2.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Project2Application implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(Project2Application.class, args);
  }


  @Autowired
  DirectorService directorService;

  @Autowired
  MovieService movieService;

  @Autowired
  MyUserService myUserService;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  public void run(String... args) throws Exception {
    Director quentinTarantino = directorService.createNewDirector("Quentin", "Tarantino");
    Director davidFincher = directorService.createNewDirector("David", "Fincher");
    Director samMendes = directorService.createNewDirector("Sam", "Mendes");
    Director peterJackson = directorService.createNewDirector("Peter", "Jackson");
    Director parkChanwook = directorService.createNewDirector("Park", "Chan-wook");

    movieService.createNewMovie("Revolutionary Road", 2008, samMendes);
    movieService.createNewMovie("Oldboy", 2003, parkChanwook);
    movieService.createNewMovie("Pulp Fiction", 1994, quentinTarantino);
    movieService.createNewMovie(
        "The Lord of the Rings: The Return of the King", 2003, peterJackson);
    movieService.createNewMovie("Gone Girl", 2014, davidFincher);

    MyUser user1 = new MyUser("admin@cit.ie", passwordEncoder.encode("password"), "ADMIN");
    MyUser user2 = new MyUser("user@cit.ie", passwordEncoder.encode("password"), "USER");
    MyUser user3 = new MyUser("api@cit.ie", passwordEncoder.encode("password"), "API");

    myUserService.createNewUser(user1);
    myUserService.createNewUser(user2);
    myUserService.createNewUser(user3);
  }
}
