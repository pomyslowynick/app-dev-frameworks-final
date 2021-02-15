package com.rrosa.project2.service;

import com.rrosa.project2.dao.MyUserDao;
import com.rrosa.project2.entities.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired MyUserDao myUserDao;

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    Optional<MyUser> optionalUser = myUserDao.findById(userName);
    if (optionalUser.isPresent()) {
      MyUser myUser = optionalUser.get();

      return User.builder()
          .username(myUser.getUserEmail())
          .password(myUser.getUserPassword())
          .disabled(false)
          .accountExpired(false)
          .accountLocked(false)
          .roles(myUser.getUserRole())
          .build();
    } else {
      throw new UsernameNotFoundException("User Name " + userName + " is not Found");
    }
  }
}
