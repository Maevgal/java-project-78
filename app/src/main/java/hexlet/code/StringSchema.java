package hexlet.code;

public class StringSchema {
    private int minLength;
    private String substring;
    private boolean required;

    public boolean isValid(String str) {
        boolean check = true;
        if (this.required) {
            check = check && !(str == null || str.isEmpty());
        }
        if (this.minLength != 0) {
            check = check && (str.length() >= this.minLength);
        }
        if (this.substring != null) {
            check = check && str.contains(substring);
        }
        return check;
    }

    public final StringSchema required() {
        this.required = true;
        return this;
    }

    public final StringSchema minLength(int minLength) {
        this.minLength = minLength;
        return this;
    }

    public final StringSchema contains(String substring) {
        this.substring = substring;
        return this;
    }
}
