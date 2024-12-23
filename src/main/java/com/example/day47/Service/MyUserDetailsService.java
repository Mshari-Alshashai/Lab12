package com.example.day47.Service;

import com.example.day47.Api.ApiException;
import com.example.day47.Model.MyUser;
import com.example.day47.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final AuthRepository authRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = authRepository.findMyUsersByUsername(username);
        if (user == null) throw new ApiException("Wrong username or password");

        return user;
    }
}
