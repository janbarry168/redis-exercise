package project.janbarry.cacheservice.controller.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import project.janbarry.cacheservice.controller.bean.ResponseBean;
import project.janbarry.cacheservice.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseBean> handleException(Exception e, HttpServletRequest request) {
        ResponseBean body = new ResponseBean();
        body.setInstance(request.getRequestURI());
        body.setTitle(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_PROBLEM_JSON).body(body);
    }

    @ExceptionHandler(ControllerException.class)
    public ResponseEntity<ResponseBean> handleControllerException(ControllerException ce, HttpServletRequest request) {
        ce.getBody().setInstance(request.getContextPath());
        return ResponseEntity.status(ce.getHttpStatus()).contentType(MediaType.APPLICATION_PROBLEM_JSON).body(ce.getBody());
    }

}
