package com.springbootapi.expections;

public class ResourceNotFoundExpection extends RuntimeException{
    public ResourceNotFoundExpection(String message) {
        super(message);
    }
}
