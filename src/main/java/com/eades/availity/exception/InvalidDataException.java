package com.eades.availity.exception;

import java.lang.Exception;

public class InvalidDataException extends Exception {
    public InvalidDataException(String msg) {
        super(msg);
    }
}