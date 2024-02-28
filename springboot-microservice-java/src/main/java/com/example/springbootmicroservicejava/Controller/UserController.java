
package com.example.springbootmicroservicejava.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.springbootmicroservicejava.Repository.UserRepository;

import jakarta.validation.Valid;

import com.Exceptions.UserNotFoundException;
import com.example.springbootmicroservicejava.Data.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.net.URI;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
class UserController {
    @Autowired
    private UserRepository userRepository;
    
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //get      
    @GetMapping("/users")
    public List<User> retriveAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User retriveAllUsers(@PathVariable int id){
        User user = userRepository.findById(id);
        if(user == null){
            throw new UserNotFoundException("id:"+id);
        }
        return user;
    }

    //post
    @PostMapping("/users")
    public ResponseEntity<User> postMethodName(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(savedUser.getId())
        .toUri();
        return ResponseEntity.created(location).build();
    }
    
    //put
    //delete

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        userRepository.delete(id);  
    }
    //putch
    
}