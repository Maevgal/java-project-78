package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {
    public BaseSchema required() {
        super.addRule("required",
                obj -> obj instanceof Map<?, ?>);
        return this;
    }

    public MapSchema sizeof(int count) {
        super.addRule("required",
                obj -> obj instanceof Map<?, ?> map && map.size() == count);
        return this;
    }

    public BaseSchema shape(Map<String, BaseSchema> schemas) {
        super.addRule("shape",
                obj -> obj instanceof Map<?, ?> map && map.entrySet().stream()
                        .allMatch(entry -> {
                            if (entry.getKey() instanceof String key) {
                                BaseSchema schema = schemas.getOrDefault(key, new BaseSchema() {
                                });
                                return schema.isValid(entry.getValue());
                            }
                            return false;
                        })
        );
        return this;
    }
}
