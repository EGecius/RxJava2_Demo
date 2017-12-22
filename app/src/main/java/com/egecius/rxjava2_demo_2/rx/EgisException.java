package com.egecius.rxjava2_demo_2.rx;

public class EgisException extends Exception {

    private final String message;

    public EgisException() {
        message = null;
    }

    public EgisException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EgisException)) return false;

        EgisException that = (EgisException) o;

        return message != null ? message.equals(that.message) : that.message == null;
    }

    @Override
    public int hashCode() {
        return message != null ? message.hashCode() : 0;
    }
}
