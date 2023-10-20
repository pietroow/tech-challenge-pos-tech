package br.com.postech.software.architecture.techchallenge.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;

@Entity
@Table(name = "cliente")
@Getter
public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, length = 14)
	private String cpf;

	@Column(nullable = false,length = 255)
	private String senha;
}
