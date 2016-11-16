package content.com.ua.enums;

public enum UserRole {
    ADMIN, CUSTOMER, WRITER;

    @Override
    public String toString() {
        return "ROLE_" + name();
    }
}
