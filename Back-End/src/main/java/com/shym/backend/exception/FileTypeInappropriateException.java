package com.shym.backend.exception;

public class FileTypeInappropriateException extends RuntimeException {
    public FileTypeInappropriateException(String actual, String... expected) {
        super("expected one of the following file types: " + String.join(", ", expected) + "but found : " + actual);
    }
}
