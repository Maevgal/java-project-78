package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ValidatorTest {
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

    @Test
    void mapShemaNotRuleNull() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        boolean actual = schema.isValid(null);
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void mapShemaNullRuleRequired() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        boolean actual = schema.required().isValid(null);
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void mapShemaNewMapRuleRequired() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        boolean actual = schema.required().isValid(new HashMap());
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void mapShemaRuleRequiredSizeOfWithoutcount() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        schema.required().isValid(new HashMap());
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        boolean actual = schema.isValid(data);
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void mapShemaRuleRequiredSizeOfFail() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        schema.required().isValid(new HashMap());
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        schema.sizeof(2);
        boolean actual = schema.isValid(data);
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void mapShemaRuleRequiredSizeOf() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        schema.required().isValid(new HashMap());
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        data.put("key2", "value2");
        schema.sizeof(2);
        boolean actual = schema.isValid(data);
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void mapShemaShape() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);
        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        boolean actual = schema.isValid(human1);
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void mapShemaShape2() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);
        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        boolean actual = schema.isValid(human2);
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void mapShemaShape3() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);
        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        boolean actual = schema.isValid(human3);
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void mapShemaShape4() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);
        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        boolean actual = schema.isValid(human4);
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }
}
