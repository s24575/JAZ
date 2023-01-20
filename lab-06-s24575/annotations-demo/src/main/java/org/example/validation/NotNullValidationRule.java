package org.example.validation;

import org.example.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class NotNullValidationRule implements ICheckValidationRule{
    @Override
    public <T> void validate(ValidationResult<T> result){
        Field[] fields = result.getValidatedObject().getClass().getDeclaredFields();
        for(var field : fields){
            if(field.isAnnotationPresent(NotNull.class)){
                field.setAccessible(true);
                try{
                    if(field.get(result.getValidatedObject()) == null){
                        result.setValid(false);
                        var notNull = field.getAnnotation(NotNull.class);
                        if(result.getNotValidFields().get(field.getName()) == null){
                            result.getNotValidFields().put(field.getName(), new ArrayList<>());
                        }
                        result.getNotValidFields().get(field.getName()).add(notNull.message());
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
