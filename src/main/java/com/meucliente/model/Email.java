package com.meucliente.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "email")
@SequenceGenerator(name = "EMAIL_SEQ", sequenceName = "email_id_seq", allocationSize=1)
public class Email implements Serializable {

  private static final long serialVersionUID = 2L;

  @Id
  @Column(name = "id", unique = true, nullable = false, length = 14)
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "EMAIL_SEQ")
  @Getter @Setter private Long id;
  
  @Column(name = "descricao")
  @Getter @Setter private String descricao;  

  @Column(name = "cpf")
  @Getter @Setter private String cpf;

  // Heranças Object
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Email email = (Email)o;
    return id.equals(email.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
  
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Email{");
    sb.append("id='").append(id);
    sb.append("', descricao='").append(descricao);
    sb.append("'}");
    return sb.toString();
  }
}
