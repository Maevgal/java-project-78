package hexlet.code;

public class StringSchema {
    private int minLengthRule;
    private String substringRule;
    private boolean requiredRule;

    public boolean isValid(String str) {
        boolean check = true;
        if (this.requiredRule) {
            check = check && !(str == null || str.isEmpty());
        }
        if (this.minLengthRule != 0) {
            check = check && (str.length() >= this.minLengthRule);
        }
        if (this.substringRule != null) {
            check = check && str.contains(this.substringRule);
        }
        return check;
    }

    public final StringSchema required() {
        this.requiredRule = true;
        return this;
    }

    public final StringSchema minLength(int minLength) {
        this.minLengthRule = minLength;
        return this;
    }

    public final StringSchema contains(String substring) {
        this.substringRule = substring;
        return this;
    }
}
