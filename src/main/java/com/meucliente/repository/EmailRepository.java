package com.meucliente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.meucliente.model.Email;

@Repository
public interface EmailRepository extends CrudRepository<Email, Integer> {

  @Query("select a from Email a where a.descricao like ?1")
  List<Email> obterPorEmail(String email);  
}