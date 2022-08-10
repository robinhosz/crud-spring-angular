package com.robson.os.controller.exception;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class ValidationErro extends StandardError implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationErro(Integer status, OffsetDateTime dataHora, String titulo) {
		super(status, dataHora, titulo);
		// TODO Auto-generated constructor stub
	}
	
	public void addError(String fieldName, String message) {
		this.errors.add(new FieldMessage(fieldName, message));
	}

}
