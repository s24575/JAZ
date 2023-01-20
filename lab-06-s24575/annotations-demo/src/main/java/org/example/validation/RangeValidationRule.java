package org.example.validation;

import org.example.annotations.Range;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class RangeValidationRule implements ICheckValidationRule{
    @Override
    public <T> void validate(ValidationResult<T> result){
        Field[] fields = result.getValidatedObject().getClass().getDeclaredFields();
        for(var field : fields){
            if(field.isAnnotationPresent(Range.class)){
                field.setAccessible(true);
                try{
                    var number = field.getInt(result.getValidatedObject());
                    var range = field.getAnnotation(Range.class);
                    if(number < range.min() || range.max() < number){
                        result.setValid(false);
                        if(result.getNotValidFields().get(field.getName()) == null){
                            result.getNotValidFields().put(field.getName(), new ArrayList<>());
                        }
                        result.getNotValidFields().get(field.getName()).add(range.message() + " [" + range.min() + "," + range.max() + "]");
                    }
                } catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
        // for (var entry : result.getNotValidFields().entrySet()) {
        //     System.out.println(entry.getKey() + "/" + entry.getValue());
        // }
    }
}
