package br.com.postech.software.architecture.techchallenge.enums;

public interface APIEnum<T> {

	public Integer getValue();
	
	T get(Integer value);
}
