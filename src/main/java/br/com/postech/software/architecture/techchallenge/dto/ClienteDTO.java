package br.com.postech.software.architecture.techchallenge.dto;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

	private Long id;
	private String nome;
	@Email
	private String email;
	@CPF
	private String cpf;
	@Min(6)
	private String senha;
	@NotNull
	private char status;
}
