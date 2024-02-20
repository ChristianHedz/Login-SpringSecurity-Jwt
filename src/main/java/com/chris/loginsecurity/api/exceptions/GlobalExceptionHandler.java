package com.chris.loginsecurity.api.exceptions;

import com.chris.loginsecurity.api.models.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest){
        ApiResponse response = new ApiResponse("No se encontro el recurso solicitado!", HttpStatus.NOT_FOUND,webRequest.getDescription(false),ex.getMessage());
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handlerException(Exception ex, WebRequest webRequest){
        ApiResponse response = new ApiResponse("Ha ocurrido un error ",HttpStatus.INTERNAL_SERVER_ERROR, webRequest.getDescription(false),ex.getMessage() );
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex,WebRequest webRequest){
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put((error).getField(),error.getDefaultMessage()));
        ApiResponse response = new ApiResponse("Los datos proporcionados no son válidos.",  HttpStatus.BAD_REQUEST,webRequest.getDescription(false),errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }




}
