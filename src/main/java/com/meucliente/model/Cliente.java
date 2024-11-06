package com.meucliente.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

  private static final long serialVersionUID = 1L;
  
  @Id
  @Column(name = "cpf")
  @Getter @Setter private String cpf;

  @Column(name = "nome")
  @Getter @Setter private String nome;
  
  @Column(name = "cep")
  @Getter @Setter private String cep;

  @Column(name = "logradouro")
  @Getter @Setter private String logradouro;
    
  @Column(name = "bairro")
  @Getter @Setter private String bairro;
    
  @Column(name = "cidade")
  @Getter @Setter private String cidade;
    
  @Column(name = "UF")
  @Getter @Setter private String uf;
  
  @Column(name = "Complemento")
  @Getter @Setter private String complemento;
  
  @OneToMany(mappedBy="cpf", targetEntity = Email.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  @JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
  @Getter @Setter private List<Email> emails; 
  
  @OneToMany(mappedBy="cpf", targetEntity = Telefone.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  @JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
  @Getter @Setter private List<Telefone> telefones; 

  // Heranças Object
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Cliente cliente = (Cliente)o;
    return cpf.equals(cliente.getCpf());
  }

  @Override
  public int hashCode() {
    return Objects.hash(cpf);
  }
  
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Cliente{");
    sb.append("CPF='").append(cpf);
    sb.append("', nome='").append(nome);
    sb.append("', cep='").append(cep);
    sb.append("', logradouro='").append(logradouro);
    sb.append("', bairro='").append(bairro);
    sb.append("', cidade='").append(cidade);
    sb.append("', uf='").append(uf);
    sb.append("', complemento='").append(complemento);
    sb.append("'}");
    return sb.toString();
  }
}