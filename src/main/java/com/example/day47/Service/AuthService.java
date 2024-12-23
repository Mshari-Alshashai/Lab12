package com.example.day47.Service;

import com.example.day47.Api.ApiException;
import com.example.day47.Model.MyUser;
import com.example.day47.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

    public void register(MyUser user) {
        user.setRole("ROLE_USER");
        String hashPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashPassword);

        authRepository.save(user);
    }

    public List<MyUser> getAllUsers() {
        return authRepository.findAll();
    }

    public void update(Integer userId, MyUser user) {
        MyUser oldUser = authRepository.findMyUserById(userId);
        if (oldUser == null) throw new ApiException("user not found");
        oldUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        oldUser.setUsername(user.getUsername());
        authRepository.save(oldUser);
    }

    public void delete(Integer userId) {
        MyUser user = authRepository.findMyUserById(userId);
        if (user == null) throw new ApiException("user not found");
        authRepository.delete(user);
    }
}
