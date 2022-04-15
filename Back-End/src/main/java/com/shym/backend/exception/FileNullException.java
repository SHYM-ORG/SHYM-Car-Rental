package com.shym.backend.exception;

public class FileNullException extends RuntimeException {
    public FileNullException() {
        super("the file uploaded is null");
    }
}
