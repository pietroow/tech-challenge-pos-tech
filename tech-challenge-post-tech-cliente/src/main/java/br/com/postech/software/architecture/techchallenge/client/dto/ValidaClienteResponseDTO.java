package br.com.postech.software.architecture.techchallenge.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder(toBuilder = true, setterPrefix = "set")
@AllArgsConstructor
@NoArgsConstructor
public class ValidaClienteResponseDTO {

    private boolean isValid;
    private String errorMessage;
    private ClienteDTO clienteDTO;
}
