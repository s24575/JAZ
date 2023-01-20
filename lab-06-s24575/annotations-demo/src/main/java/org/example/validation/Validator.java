package org.example.validation;

import java.util.ArrayList;
import java.util.List;

public class Validator {
    List<ICheckValidationRule> rules = new ArrayList<>();
    public <T> ValidationResult<T> validate(T sampleObject) {
        var result = new ValidationResult<T>();
        result.setValidatedObject(sampleObject);
        result.setValid(true);
        for(var rule : rules){
            rule.validate(result);
        }
        // for (var entry : result.getNotValidFields().entrySet()) {
        //     System.out.println(entry.getKey() + "/" + entry.getValue());
        // }
        return result;
    }

    public Validator addRule(ICheckValidationRule rule){
        rules.add(rule);
        return this;
    }
}
