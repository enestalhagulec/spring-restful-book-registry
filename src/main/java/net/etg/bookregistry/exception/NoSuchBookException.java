package net.etg.bookregistry.exception;

public class NoSuchBookException extends RuntimeException{

    public NoSuchBookException(String message) {
        super(message);
    }
}
