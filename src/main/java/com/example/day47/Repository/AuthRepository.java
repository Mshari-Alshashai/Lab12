package com.example.day47.Repository;

import com.example.day47.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<MyUser,Integer> {
    MyUser findMyUsersByUsername(String username);
    MyUser findMyUserById(Integer id);
}
