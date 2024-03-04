package com.example.springbootmicroservicejava.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.springbootmicroservicejava.Repository.IPostRepository;

@RestController
public class PostController {

    private IPostRepository postRepository;

    public PostController(IPostRepository postRepository)
    {
        this.postRepository = postRepository;
    }
    
}
