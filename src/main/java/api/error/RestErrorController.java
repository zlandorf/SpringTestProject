package api.error;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class RestErrorController implements org.springframework.boot.autoconfigure.web.ErrorController {
	
	private static final String ERROR_PATH = "/error";
	
	@RequestMapping(value=ERROR_PATH)
	public Error error(HttpServletRequest request, HttpServletResponse response) {
		return new Error(response.getStatus(), HttpStatus.valueOf(response.getStatus()).getReasonPhrase());
	}
	
	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Not found")
	@ExceptionHandler(ResourceNotFoundException.class)
	public Error notFoundException(RuntimeException e) {
		return new Error(HttpStatus.NOT_FOUND.value(), e.getMessage());
	}
}
