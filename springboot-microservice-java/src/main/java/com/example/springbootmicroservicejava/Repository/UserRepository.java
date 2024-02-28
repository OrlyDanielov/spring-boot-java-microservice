package com.example.springbootmicroservicejava.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.example.springbootmicroservicejava.Data.User;

@Component
public class UserRepository {

    //JPA/ HIBERNET -> DB

    private static List<User> users = new ArrayList<>();

    private static int usersCount = 0;

    static{
        users.add(new User(++usersCount,"Orly",LocalDate.now()));
        users.add(new User(++usersCount,"Eyal",LocalDate.now().plusDays(-2)));
        users.add(new User(++usersCount,"Michael",LocalDate.now().plusDays(-10)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findById(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);      
    }

    public User save(User user){
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

    public void delete(int id){
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }

    //PUBLIC List<User> findAll()
    //public User save(User user)
    //public User findOne(int id)
    
}
