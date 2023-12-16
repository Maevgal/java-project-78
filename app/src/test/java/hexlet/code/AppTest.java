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
        schema.required();
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

    @Test
    void numberShemaNotRuleNumberNull() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        boolean actual = schema.isValid(null); // true
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void numberShemaNotRuleNumberNullRulePositive() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        boolean actual = schema.positive().isValid(null);
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void numberShemaNotRuleRuleRequiredNumberNull() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        boolean actual = schema.required().isValid(null);
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void numberShemaNotRuleRuleRequiredNumber() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        schema.positive();
        boolean actual = schema.required().isValid(10);
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void numberShemaNotRuleRuleRequiredPositive() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        schema.positive();
        boolean actual = schema.required().isValid(-10);
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void numberShemaNotRuleRuleRequiredPositiveNumber0() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        schema.positive();
        boolean actual = schema.required().isValid(0);
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void numberShemaNotRuleRuleRequiredRange1() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        schema.required();
        boolean actual = schema.range(5, 10).isValid(5);
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void numberShemaNotRuleRuleRequiredRange2() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        schema.required();
        boolean actual = schema.range(5, 10).isValid(10);
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void numberShemaNotRuleRuleRequiredRange3() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        schema.required();
        boolean actual = schema.range(5, 10).isValid(4);
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }
}
