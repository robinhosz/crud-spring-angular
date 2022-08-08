package com.robson.os.enums;

import lombok.Getter;

@Getter
public enum Prioridade {

	BAIXA(0, "Baixa"), MEDIA(1, "Media"), ALTA(2, "Alta");
	
	private Integer cod;
	private String desc;
	
	private Prioridade(Integer cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}
	
	public static Prioridade toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(Prioridade x : Prioridade.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Prioridade inválida!" + cod);
	}
	
}
