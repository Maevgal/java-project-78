package hexlet.code;

public class StringSchema extends BaseSchema {
    private int minLengthRule;
    private String substringRule;

    public StringSchema required() {
        this.requiredRule = true;
        return this;
    }

    public StringSchema minLength(int minLength) {
        this.minLengthRule = minLength;
        return this;
    }

    public StringSchema contains(String substring) {
        this.substringRule = substring;
        return this;
    }

    @Override
    public boolean isValid(Object obj) {
        if (obj == null) {
            if (!this.requiredRule) {
                return true;
            }
            return false;

        } else {
            String str = obj.toString();
            boolean check = true;
            if (this.requiredRule) {
                check = check && !(obj == null || str.isEmpty());
                if (this.minLengthRule != 0) {
                    check = check && (str.length() >= this.minLengthRule);
                }
                if (this.substringRule != null) {
                    check = check && str.contains(this.substringRule);
                }
                return check;
            }
            return true;
        }
    }
}
