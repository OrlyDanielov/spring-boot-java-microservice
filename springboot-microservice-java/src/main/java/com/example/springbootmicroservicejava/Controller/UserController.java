
package com.example.springbootmicroservicejava.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.springbootmicroservicejava.Repository.IPostRepository;
import com.example.springbootmicroservicejava.Repository.IUserRepository;
import com.example.springbootmicroservicejava.Repository.UserRepository;

import jakarta.validation.Valid;

import com.example.springbootmicroservicejava.Data.Post;
import com.example.springbootmicroservicejava.Data.User;
import com.example.springbootmicroservicejava.Exceptions.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
class UserController 
{    
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IPostRepository postRepository;
    
    public UserController(IUserRepository userRepository, IPostRepository postRepository){
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }
    
    @GetMapping("/users")
    public List<User> retriveAllUsers(){
        return userRepository.findAll();
    }
    
    @GetMapping("/users/{id}")
    public User retriveAllUsers(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("id:"+id);
        }
        return user.get();
    }

    //post
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(savedUser.getId())
        .toUri();
        return ResponseEntity.created(location).build();
    }
    
    //put
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable int id , @Valid @RequestBody User user){
        Optional<User> savedUser = userRepository.findById(id);
        if(savedUser.isEmpty()){
            throw new UserNotFoundException("id:"+id);
        }
        
        savedUser.get().setName(user.getName());
        savedUser.get().setBirthDate(user.getBirthDate());
        User updatedUser = userRepository.save(savedUser.get());

        return updatedUser;
    }
    
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        userRepository.deleteById(id);  
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> retrivePostsForUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("id : "+ id);
        }
        return user.get().getPosts();        
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post)
    {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("id : "+ id);
        }

        post.setUser(user.get());
        Post savedPost =  postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(savedPost.getId())
        .toUri();
        return ResponseEntity.created(location).build();
      }

}