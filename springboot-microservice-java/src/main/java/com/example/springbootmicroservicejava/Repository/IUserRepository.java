package com.example.springbootmicroservicejava.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springbootmicroservicejava.Data.User;

public interface IUserRepository extends JpaRepository<User,Integer>
{
        
}