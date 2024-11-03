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
@Table(name = "telefone")
@SequenceGenerator(name = "TELEFONE_SEQ", sequenceName = "telefone_id_seq", allocationSize=1)
public class Telefone implements Serializable {

  private static final long serialVersionUID = 2L;

  @Id
  @Column(name = "id", unique = true, nullable = false, length = 14)
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "TELEFONE_SEQ")
  @Getter @Setter private Long id;

  @Column(name = "tipo")
  @Getter @Setter private String tipo;
  
  @Column(name = "numero")
  @Getter @Setter private String numero;

  @Column(name = "cpf")
  @Getter @Setter private String cpf;

  // Heranças Object
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Telefone telefone = (Telefone)o;
    return id.equals(telefone.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
  
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Email{");
    sb.append("id='").append(id);
    sb.append("', tipo='").append(tipo);
    sb.append("', numero='").append(numero);
    sb.append("'}");
    return sb.toString();
  }
}