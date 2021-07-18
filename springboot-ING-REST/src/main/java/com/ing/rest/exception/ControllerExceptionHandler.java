package com.ing.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler 
{
   @ExceptionHandler
   public ResponseEntity<Object> dataNotFoundHandler(DataNotFoundException exception) {
      return new ResponseEntity<Object>("No Data found", HttpStatus.NOT_FOUND);
   }
   
   @ExceptionHandler
   public ResponseEntity<Object> jsonMapexceptionHandler(JsonMapException exception) {
      return new ResponseEntity<Object>("Json Response Mapping Exception. Please Contact System administartor.", HttpStatus.INTERNAL_SERVER_ERROR);
   }
   
   @ExceptionHandler
   public ResponseEntity<Object> jsonProcessExceptionHanler(JsonProcessException exception) {
      return new ResponseEntity<Object>("Json Response Processing Exception. Please Contact System administartor.", HttpStatus.INTERNAL_SERVER_ERROR);
   }
   
}
