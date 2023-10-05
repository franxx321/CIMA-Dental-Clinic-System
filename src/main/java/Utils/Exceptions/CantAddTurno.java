package Utils.Exceptions;

public class CantAddTurno extends RuntimeException{
    String errors;

    public String getErrors() {
        return errors;
    }

    public CantAddTurno(String errors) {
        this.errors = errors;
    }
}
