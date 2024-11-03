package com.meucliente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.meucliente.model.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, String> {

  @Query("select a from Cliente a where a.nome like ?1")
  List<Cliente> obterPorNome(String nome);  
}
