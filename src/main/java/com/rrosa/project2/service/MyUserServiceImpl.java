package com.rrosa.project2.service;

import com.rrosa.project2.dao.MyUserDao;
import com.rrosa.project2.entities.MyUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class MyUserServiceImpl implements MyUserService {

  @Autowired MyUserDao myUserDao;

  @Override
  public MyUser createNewUser(MyUser newUser) {
    if (myUserDao.existsByUserEmail(newUser.getUserEmail())) {
      log.error("Provided email is already in use.");
      return null;
    }
    return myUserDao.save(newUser);
  }
}
