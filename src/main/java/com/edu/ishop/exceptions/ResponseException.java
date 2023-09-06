package com.edu.ishop.exceptions;


public class ResponseException extends Exception {
    public ResponseException(String errorText){
           super(errorText);
    }
}
