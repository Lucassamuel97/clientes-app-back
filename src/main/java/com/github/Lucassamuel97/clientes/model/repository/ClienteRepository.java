package com.github.Lucassamuel97.clientes.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.Lucassamuel97.clientes.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
