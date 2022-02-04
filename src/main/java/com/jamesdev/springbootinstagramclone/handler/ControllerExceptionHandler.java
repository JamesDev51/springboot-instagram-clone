package com.jamesdev.springbootinstagramclone.handler;

import com.jamesdev.springbootinstagramclone.dto.ResponseDto;
import com.jamesdev.springbootinstagramclone.handler.ex.CustomApiException;
import com.jamesdev.springbootinstagramclone.handler.ex.CustomException;
import com.jamesdev.springbootinstagramclone.handler.ex.CustomValidationApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

      @ExceptionHandler(CustomValidationApiException.class)
      public ResponseEntity<?> validationApiException(CustomValidationApiException e){
            return new ResponseEntity<>(new ResponseDto<>(e.getErrorMap()), HttpStatus.BAD_REQUEST);
      }
      @ExceptionHandler(CustomException.class)
      public ResponseEntity<?> validationApiException(CustomException e){
            return new ResponseEntity<>(new ResponseDto<>(e.getMessage()), HttpStatus.BAD_REQUEST);
      }

      @ExceptionHandler(CustomApiException.class)
      public ResponseEntity<?> apiException(CustomApiException e){
            return new ResponseEntity<>(new ResponseDto<>(e.getMessage()), HttpStatus.BAD_REQUEST);
      }
}
