package com.rrosa.project2.dao;

import com.rrosa.project2.entities.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserDao extends JpaRepository<MyUser, String> {
  boolean existsByUserEmail(String userEmail);
}
