package com.springbootapi.expections;

import com.springbootapi.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandleException {

    @ExceptionHandler(ResourceNotFoundExpection.class)
    public ResponseEntity<ApiResponse<Void>> ResourceNotFound(ResourceNotFoundExpection ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ApiResponse<>(
                        ex.getMessage(),
                        "error",
                        null
                )
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> IllegalArgument(IllegalArgumentException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ApiResponse<>(
                        ex.getMessage(),
                        "erro",
                        null
                )
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> ExpectionGeneric(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse<>(
                        ex.getMessage(),
                        "error",
                        null
                )
        );
    }
}
