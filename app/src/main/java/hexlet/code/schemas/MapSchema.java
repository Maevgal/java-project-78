package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {
    private int countRule;
    private Map<String, BaseSchema> schemasRule;

    public MapSchema sizeof(int count) {
        this.countRule = count;
        return this;
    }

    @Override
    public BaseSchema required() {
        this.requiredRule = true;
        return this;
    }

    @Override
    public boolean isValid(Object obj) {
        if (obj == null) {
            return !this.requiredRule;
        } else {
            Map<String, Object> data = (Map<String, Object>) obj;
            boolean check = true;
            if (this.countRule != 0) {
                check = countRule == data.size();
            }
            if (this.schemasRule != null) {
                check = check && data.entrySet().stream()
                        .filter(entry -> schemasRule.containsKey(entry.getKey()))
                        .map(entry -> schemasRule.get(entry.getKey()).isValid(entry.getValue()))
                        .reduce(check, (v1, v2) -> v1 && v2);
                /*for (Map.Entry<String, Object> entry : data.entrySet()) {
                    if (schemasRule.containsKey(entry.getKey())) {
                        check = check && schemasRule.get(entry.getKey()).isValid(entry.getValue());
                    }
                }*/
            }
            return check;
        }
    }

    public BaseSchema shape(Map<String, BaseSchema> schemas) {
        this.schemasRule = schemas;
        return this;
    }
}
