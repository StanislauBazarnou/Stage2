package by.epam.learn.exceptions.exception;

public class NullStudentException extends Exception {
    public NullStudentException() {
    }

    public NullStudentException(String s) {
        super(s);
    }

    public NullStudentException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public NullStudentException(Throwable throwable) {
        super(throwable);
    }

    public NullStudentException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
