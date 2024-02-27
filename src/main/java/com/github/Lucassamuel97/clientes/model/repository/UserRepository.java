package com.github.Lucassamuel97.clientes.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.github.Lucassamuel97.clientes.model.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	
	UserDetails findByLogin(String login);
}
