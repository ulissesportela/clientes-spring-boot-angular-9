package com.guthub.ulissesportela.clientes.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guthub.ulissesportela.clientes.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
