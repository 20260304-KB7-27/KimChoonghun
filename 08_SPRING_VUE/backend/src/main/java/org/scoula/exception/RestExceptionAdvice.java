package org.scoula.exception;


import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.core.config.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@Log4j2
@Order(2)
public class RestExceptionAdvice {

    // 404 - 리소스 찾을 수 없음
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handle404(NoHandlerFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND) // 404
                .header("Content-Type", "text/plain;charset=UTF-8")
                .body("🍣해당 ID의 요소가 없습니다.");
    }
}