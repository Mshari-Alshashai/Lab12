package com.example.day47.Service;

import com.example.day47.Api.ApiException;
import com.example.day47.Model.Blog;
import com.example.day47.Model.MyUser;
import com.example.day47.Repository.AuthRepository;
import com.example.day47.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;


    public List<Blog> getMyBlogs(Integer userId) {
        MyUser user = authRepository.findMyUserById(userId);
        if(user == null) throw new ApiException("user not found");
        return blogRepository.findAllByUser(user);
    }

    public void addBlog(Integer userId, Blog blog) {
        MyUser user = authRepository.findMyUserById(userId);
        if(user == null) throw new ApiException("user not found");
        blog.setUser(user);
        blogRepository.save(blog);
    }

    public void updateBlog(Integer userId, Integer blogId, Blog blog) {
        MyUser user = authRepository.findMyUserById(userId);
        if(user == null) throw new ApiException("user not found");

        Blog oldBlog = blogRepository.findBlogById(blogId);
        if(oldBlog == null) throw new ApiException("blog not found");
        if(!oldBlog.getUser().equals(user)) throw new ApiException("user not match");

        blog.setTitle(oldBlog.getTitle());
        blog.setBody(oldBlog.getBody());
        blogRepository.save(blog);
    }

    public void deleteBlog(Integer userId, Integer blogId) {
        MyUser user = authRepository.findMyUserById(userId);
        if(user == null) throw new ApiException("user not found");

        Blog blog = blogRepository.findBlogById(blogId);
        if(blog == null) throw new ApiException("blog not found");
        if(!blog.getUser().equals(user)) throw new ApiException("user not match");

        blogRepository.delete(blog);
    }


    public List<Blog> getAllUserBlogs(Integer userId) {
        MyUser user = authRepository.findMyUserById(userId);
        if(user == null) throw new ApiException("user not found");
        return blogRepository.findAllByUser(user);

    }

    public Blog getBlogById(Integer blogId) {
        Blog blog = blogRepository.findBlogById(blogId);
        if(blog == null) throw new ApiException("blog not found");
        return blog;
    }

    public Blog getBlogByTitle(String title) {
        Blog blog = blogRepository.findBlogByTitle(title);
        if(blog == null) throw new ApiException("blog not found");
        return blog;
    }
}
