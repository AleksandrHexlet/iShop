package com.edu.ishop.helpers.exceptions;


public class ResponseException extends Exception {
    public ResponseException(String errorText){
           super(errorText);
    }
}
