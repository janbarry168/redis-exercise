package project.janbarry.cacheservice.controller.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import project.janbarry.cacheservice.controller.bean.ResponseBean;

@Getter
public class ControllerException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final ResponseBean body;

    public ControllerException(HttpStatus httpStatus, String title, String detail) {
        this.httpStatus = httpStatus;
        this.body = new ResponseBean(title, detail, null, null);
    }

}
