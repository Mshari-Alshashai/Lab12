package com.example.day47.Controller;

import com.example.day47.Api.ApiResponse;
import com.example.day47.Model.MyUser;
import com.example.day47.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/get-all-users")
    public ResponseEntity getAllUsers(@AuthenticationPrincipal MyUser user) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.getAllUsers());
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid MyUser user) {
        authService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("user registered successfully"));
    }

    @PutMapping("/update")
    public ResponseEntity update(@AuthenticationPrincipal MyUser user, @RequestBody @Valid MyUser updatedUser) {
        authService.update(user.getId(), updatedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("user updated successfully"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@AuthenticationPrincipal MyUser user) {
        authService.delete(user.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("user deleted successfully"));
    }
}
