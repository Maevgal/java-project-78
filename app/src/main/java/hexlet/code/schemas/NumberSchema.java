package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {
    private boolean positiveRule;
    private Integer minRangeValue;
    private Integer maxRangeValue;

    @Override
    public boolean isValid(Object obj) {
        if (obj == null) {
            return !this.requiredRule;
        } else {
            if (obj instanceof Integer) {
                Integer number = (Integer) obj;
                boolean check = true;
                if (this.positiveRule) {
                    check = number > 0;
                }
                if (this.minRangeValue != null && this.maxRangeValue != null) {
                    check = check && (number >= this.minRangeValue && number <= this.maxRangeValue);
                }
                return check;
            }
            return false;
        }
    }

    public NumberSchema required() {
        this.requiredRule = true;
        return this;
    }

    public NumberSchema positive() {
        this.positiveRule = true;
        return this;
    }

    public NumberSchema range(int minValue, int maxValue) {
        this.minRangeValue = minValue;
        this.maxRangeValue = maxValue;
        return this;
    }
}
