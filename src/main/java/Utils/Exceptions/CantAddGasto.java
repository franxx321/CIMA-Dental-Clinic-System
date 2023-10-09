package Utils.Exceptions;

public class CantAddGasto extends RuntimeException {
    String errors;

    public String getErrors() {
        return errors;
    }

    public CantAddGasto(String errors) {
        this.errors = errors;
    }
}
