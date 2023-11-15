package com.example.swagger.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.swagger.api.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	
}
