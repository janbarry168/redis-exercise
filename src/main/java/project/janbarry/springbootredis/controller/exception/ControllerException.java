package project.janbarry.springbootredis.controller.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import project.janbarry.springbootredis.controller.bean.ResponseBean;

@Getter
public class ControllerException extends RuntimeException {

    private final HttpStatus httpStatus = HttpStatus.OK;
    private final ResponseBean body;

    public ControllerException(String title, String detail) {
        this.body = new ResponseBean(title, detail, null, null);
    }

}
