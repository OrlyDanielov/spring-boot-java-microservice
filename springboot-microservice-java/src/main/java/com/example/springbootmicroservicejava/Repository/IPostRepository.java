package com.example.springbootmicroservicejava.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbootmicroservicejava.Data.Post;

public interface IPostRepository extends  JpaRepository<Post,Integer>  {

}