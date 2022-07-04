package project.janbarry.cacheservice.controller.exception.handler;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import project.janbarry.cacheservice.controller.bean.ResponseBean;
import project.janbarry.cacheservice.controller.exception.ControllerException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ControllerException.class)
    public ResponseEntity<ResponseBean> handleSystemException(ControllerException ce) {
        return ResponseEntity.status(ce.getHttpStatus()).contentType(MediaType.APPLICATION_PROBLEM_JSON).body(ce.getBody());
    }

}
