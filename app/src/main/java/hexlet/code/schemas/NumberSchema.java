package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {
    public NumberSchema required() {
        super.addRule("required", obj -> obj instanceof Integer num);
        return this;
    }

    public NumberSchema positive() {
        super.addRule("positive", obj -> obj instanceof Integer num && num > 0);
        return this;
    }

    public NumberSchema range(int minValue, int maxValue) {
        super.addRule("range", obj -> obj instanceof Integer num && num >= minValue && num <= maxValue);
        return this;
    }
}
