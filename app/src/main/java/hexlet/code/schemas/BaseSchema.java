package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private final Map<String, Predicate<Object>> rule = new HashMap<>();

    protected final void addRule(String key, Predicate<Object> predicate) {
        this.rule.put(key, predicate);
    }

    public final Boolean isValid(Object obj) {
        return (obj == null && !this.rule.containsKey("required"))
                || this.rule.values().stream()
                .allMatch(predicate -> predicate.test(obj));
    }
}
