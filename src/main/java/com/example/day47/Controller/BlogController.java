package com.example.day47.Controller;

import com.example.day47.Api.ApiResponse;
import com.example.day47.Model.Blog;
import com.example.day47.Model.MyUser;
import com.example.day47.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/blog")
public class BlogController {
    private final BlogService blogService;

    @GetMapping("/get-my-blogs")
    public ResponseEntity getMyBlogs(@AuthenticationPrincipal MyUser user) {
       return ResponseEntity.status(200).body(blogService.getMyBlogs(user.getId()));
    }

    @PostMapping("/add")
    public ResponseEntity addBlog(@AuthenticationPrincipal MyUser user, @RequestBody @Valid Blog blog) {
        blogService.addBlog(user.getId(), blog);
        return ResponseEntity.status(200).body(new ApiResponse("blog added successfully"));
    }

    @PutMapping("/update/{blogId}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal MyUser user, @PathVariable Integer blogId, @RequestBody @Valid Blog blog) {
        blogService.updateBlog(user.getId(), blogId, blog);
        return ResponseEntity.status(200).body(new ApiResponse("blog updated successfully"));
    }

    @DeleteMapping("/delete/{blogId}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal MyUser user, @PathVariable Integer blogId) {
        blogService.deleteBlog(user.getId(), blogId);
        return ResponseEntity.status(200).body(new ApiResponse("blog deleted successfully"));
    }

    @GetMapping("/get-all-user-blogs/{userId}")
    public ResponseEntity getAllUserBlogs(@AuthenticationPrincipal MyUser user, @PathVariable Integer userId) {
        return ResponseEntity.status(200).body(blogService.getAllUserBlogs(userId));
    }

    @GetMapping("/get-by-id/{blogId}")
    public ResponseEntity getBlogById(@PathVariable Integer blogId) {
        return ResponseEntity.status(200).body(blogService.getBlogById(blogId));
    }

    @GetMapping("/get-by-title/{title}")
    public ResponseEntity getBlogByTitle(@AuthenticationPrincipal MyUser user, @PathVariable String title) {
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(title));
    }
}
