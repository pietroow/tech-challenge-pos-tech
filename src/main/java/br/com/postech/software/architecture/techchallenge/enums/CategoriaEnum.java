package br.com.postech.software.architecture.techchallenge.enums;

public enum CategoriaEnum {
    LANCHE("lanche"),
    ACOMPANHAMENTO("acompanhamento"),
    BEBIDA("bebida"),
    SOBREMESA("sobremesa");

    private final String value;

    CategoriaEnum(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
