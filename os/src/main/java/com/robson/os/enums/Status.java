package com.robson.os.enums;

import lombok.Getter;

@Getter
public enum Status {

ABERTO(0, "Aberto"), ANDAMENTO(1, "Andamento"), ENCERRADO(2, "Encerrado");
	
	private Integer cod;
	private String desc;
	
	private Status(Integer cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}
	
	public static Status toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(Status x : Status.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Status inválido!" + cod);
	}
	
}
