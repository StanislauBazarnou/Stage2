package by.epam.learn.exceptions.exception;

public class WrongMarkException extends Exception{
    public WrongMarkException() {
    }

    public WrongMarkException(String s) {
        super(s);
    }

    public WrongMarkException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public WrongMarkException(Throwable throwable) {
        super(throwable);
    }

    public WrongMarkException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
