package by.epam.learn.exceptions.exception;

public class NullGroupException extends Exception {
    public NullGroupException() {
    }

    public NullGroupException(String s) {
        super(s);
    }

    public NullGroupException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public NullGroupException(Throwable throwable) {
        super(throwable);
    }

    public NullGroupException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
