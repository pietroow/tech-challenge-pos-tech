package br.com.postech.software.architecture.techchallenge.pagamento.enums;

public enum StatusPagamentoEnum implements APIEnum{
	
	DESCONHECIDO(0, "Desconhecido"),
	APROVADO(1, "Aprovado"),
	PENDENTE(2, "Pendente"),
	REPROVADO(3, "Reprovado");
	
	private Integer value;
	private String descricao;
	
	private StatusPagamentoEnum(Integer value, String descricao) {
		this.value = value;
		this.descricao = descricao;
	}
	
	@Override
	public Integer getValue() {
		return value;
	}

	public String getDescricao() {
		return descricao;
	}

	public static StatusPagamentoEnum get(Integer value) {
		for (StatusPagamentoEnum categoria : StatusPagamentoEnum.values()) {
			if(categoria.getValue() == value ) {
				return categoria;
			}
		}
		
		return StatusPagamentoEnum.DESCONHECIDO;
	}
}