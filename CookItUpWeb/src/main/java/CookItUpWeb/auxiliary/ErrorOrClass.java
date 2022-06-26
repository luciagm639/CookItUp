package CookItUpWeb.auxiliary;

public class ErrorOrClass<T> {

    T t;
    String message;
    boolean error;

    public ErrorOrClass(T t) {
        this.t = t;
        this.message = null;
        this.error = false;
    }

    public ErrorOrClass(String message) {
        this.t = null;
        this.message = message;
        this.error = true;
    }

    public T getT() {
        return t;
    }

    public String getMessage() {
        return message;
    }

    public boolean isError() {
        return error;
    }
}
