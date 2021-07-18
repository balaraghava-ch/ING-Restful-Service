package com.ing.rest.exception;

public class JsonMapException extends RuntimeException {
    private static final long serialVersionUID=2L;
    
    public JsonMapException(String message)
    {
   	 super(message);
    }
}