package com.example.day47.Repository;

import com.example.day47.Model.Blog;
import com.example.day47.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    Blog findBlogById(Integer id);
    Blog findBlogByTitle(String title);

    List<Blog> findAllByUser(MyUser user);
}
