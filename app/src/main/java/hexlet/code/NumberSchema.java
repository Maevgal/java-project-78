package hexlet.code;

public final class NumberSchema extends BaseSchema {
    private boolean positiveRule;
    private Integer minRangeValue;
    private Integer maxRangeValue;

    @Override
    public boolean isValid(Object obj) {
        Integer number = (Integer) obj;
        boolean check = true;
        if (this.requiredRule) {
            check = check && !(number == null);
            if (this.positiveRule) {
                check = check && (number > 0);
            }
            if (this.minRangeValue != null && this.maxRangeValue != null) {
                check = check && (number >= this.minRangeValue && number <= this.maxRangeValue);
            }
        }
        return check;
    }

    public NumberSchema required() {
        this.requiredRule = true;
        return this;
    }

    public final NumberSchema positive() {
        this.positiveRule = true;
        return this;
    }

    public final NumberSchema range(int minValue, int maxValue) {
        this.minRangeValue = minValue;
        this.maxRangeValue = maxValue;
        return this;
    }
}