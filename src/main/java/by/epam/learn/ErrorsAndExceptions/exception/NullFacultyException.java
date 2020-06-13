package by.epam.learn.ErrorsAndExceptions.exception;

public class NullFacultyException extends Exception{
    public NullFacultyException() {
    }

    public NullFacultyException(String s) {
        super(s);
    }

    public NullFacultyException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public NullFacultyException(Throwable throwable) {
        super(throwable);
    }

    public NullFacultyException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
