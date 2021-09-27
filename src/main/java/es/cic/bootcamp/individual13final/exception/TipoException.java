package es.cic.bootcamp.individual13final.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TipoException extends RuntimeException {

	public TipoException(String message, Throwable cause) {
		super(message, cause);
	}

	public TipoException(String message) {
		super(message);
	
	}
}