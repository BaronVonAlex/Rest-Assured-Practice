package ge.tbcacad.enums;

public enum StatusAvailability {
    STATUS_AVAILABLE("available"),
    STATUS_PENDING("pending"),
    STATUS_SOLD("sold");

    private final String value;

    StatusAvailability(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
