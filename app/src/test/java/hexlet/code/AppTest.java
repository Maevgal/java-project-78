package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppTest {
    @Test
    void stringShemaNotRuleEmptyString() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        boolean actual = schema.isValid("");
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void stringShemaNotRuleNullString() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        boolean actual = schema.isValid(null);
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void stringShemaRuleRequiredNullString() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.required();
        boolean actual = schema.isValid(null);
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void stringShemaRuleRequiredEmptyString() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.required();
        boolean actual = schema.isValid("");
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void stringShemaRuleRequiredString() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.required();
        boolean actual = schema.isValid("what does the fox say");
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void stringShemaWitAllRules() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        boolean actual = schema.required().minLength(4).contains("what").isValid("what does the fox say");
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void stringShemaWitAllFalseRules() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        boolean actual = schema.required().minLength(40).contains("whatdoes").isValid("what does the fox say");
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void stringShemaRuleContainsFalse() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.contains("wh");
        schema.contains("what");
        schema.contains("whatthe");
        boolean actual = schema.isValid("what does the fox say");
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void stringShemaRuleContainsTrue() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.contains("wh");
        schema.contains("what");
        boolean actual = schema.isValid("what does the fox say");
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }


}
