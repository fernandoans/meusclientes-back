package com.meucliente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.meucliente.model.Telefone;

@Repository
public interface TelefoneRepository extends CrudRepository<Telefone, Integer> {

  @Query("select a from Telefone a where a.numero like ?1")
  List<Telefone> obterPorNumero(String numero);  
}
