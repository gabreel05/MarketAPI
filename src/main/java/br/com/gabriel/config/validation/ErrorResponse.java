package br.com.gabriel.config.validation;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class ErrorResponse {

	public ErrorResponse(Integer status, String error, String message, String path) {
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	private LocalDateTime timestamp = LocalDateTime.now();
	private Integer status;
	private String error;
	private String message;
	private String path;

}
