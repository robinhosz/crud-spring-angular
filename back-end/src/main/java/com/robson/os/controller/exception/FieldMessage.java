package com.robson.os.controller.exception;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldMessage implements Serializable{

	private static final long serialVersionUID = 1L;

	private String fieldName;
	private String message;
}
