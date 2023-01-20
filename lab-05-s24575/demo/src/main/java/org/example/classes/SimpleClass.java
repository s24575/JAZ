package org.example.classes;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;

import org.example.methods.IMethodFacade;
import org.example.methods.SimpleMethod;

public class SimpleClass implements IClassFacade {
    private Class<?> clazz;
    public SimpleClass(Class<?> clazz){
        this.clazz = clazz;
    }

    public List<IMethodFacade> getPublicDeclaredMethods(){
        List<IMethodFacade> publicMethods = new ArrayList<>();
        for(var method : clazz.getDeclaredMethods()){
            IMethodFacade simpleMethod = new SimpleMethod(method);
            if(simpleMethod.isPublic()){
                publicMethods.add(simpleMethod);
            }
        }
        return publicMethods;
    }

    public List<IMethodFacade> getPublicSetters(){
        List<IMethodFacade> publicMethods = new ArrayList<>();
        for(var method : clazz.getDeclaredMethods()){
            IMethodFacade simpleMethod = new SimpleMethod(method);
            if(simpleMethod.isSetter()){
                publicMethods.add(simpleMethod);
            }
        }
        return publicMethods;
    }

    public List<IMethodFacade> getPublicGetters(){
        List<IMethodFacade> publicMethods = new ArrayList<>();
        for(var method : clazz.getDeclaredMethods()){
            IMethodFacade simpleMethod = new SimpleMethod(method);
            if(simpleMethod.isGetter()){
                publicMethods.add(simpleMethod);
            }
        }
        return publicMethods;
    }

    public List<Field> getFieldsForPublicProperties(){
        List<Field> fields = new ArrayList<>();
        for(var field : clazz.getDeclaredFields()){
            String name = field.getName();
            if(field.getType() == Boolean.TYPE) name = Character.toLowerCase(field.getName().charAt(2)) + field.getName().substring(3);
            int counter = 0;
            for(var m : clazz.getDeclaredMethods()){
                IMethodFacade method = new SimpleMethod(m);
                if(method.isGetter() || method.isSetter()){
                    if(method.getFieldName().equals(name)){
                        ++counter;
                    }
                }
            }
            if(counter == 2) fields.add(field);            
        }
        return fields;
    }
}
