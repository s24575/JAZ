package org.example.validation;

import org.example.annotations.Regex;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexValidationRule implements ICheckValidationRule{
    @Override
    public <T> void validate(ValidationResult<T> result) {
        Field[] fields = result.getValidatedObject().getClass().getDeclaredFields();
        for(var field : fields){
            if(field.isAnnotationPresent(Regex.class)){
                field.setAccessible(true);
                try{
                    var regex = field.getAnnotation(Regex.class);
                    Pattern pattern = Pattern.compile(regex.pattern());
                    Matcher matcher = pattern.matcher((String)field.get(result.getValidatedObject()));
                    if(!matcher.find()){
                        result.setValid(false);
                        if(result.getNotValidFields().get(field.getName()) == null){
                            result.getNotValidFields().put(field.getName(), new ArrayList<>());
                        }
                        result.getNotValidFields().get(field.getName()).add(regex.message());
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
