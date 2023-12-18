package hexlet.code;

import java.util.Map;

public class MapSchema extends BaseSchema {
    private int countRule;

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
            Map<String, String> data = (Map<String, String>) obj;
            boolean check = true;
            if (this.countRule != 0) {
                check = countRule == data.size();
            }
            return check;
        }
    }
}
