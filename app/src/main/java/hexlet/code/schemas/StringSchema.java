package hexlet.code.schemas;

public class StringSchema extends BaseSchema {
    public StringSchema required() {
        super.addRule("required",
                obj -> obj instanceof String string && !string.isEmpty());
        return this;
    }

    public StringSchema minLength(int minLength) {
        super.addRule("minLength", (obj) -> obj instanceof String string
                && string.length() >= minLength);
        return this;
    }

    public StringSchema contains(String substring) {
        super.addRule("contains", (obj) -> obj instanceof String string
                && (substring == null || string.contains(substring)));
        return this;
    }
}
