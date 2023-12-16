package hexlet.code;

public abstract class BaseSchema {
    protected boolean requiredRule;

    public abstract BaseSchema required();

    public abstract boolean isValid(Object obj);
}
