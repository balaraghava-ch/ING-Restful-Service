package com.ing.rest.exception;

public class JsonProcessException extends RuntimeException {
    private static final long serialVersionUID=2L;
    
    public JsonProcessException(String message)
    {
   	 super(message);
    }
}
