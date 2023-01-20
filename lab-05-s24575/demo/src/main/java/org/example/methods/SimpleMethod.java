package org.example.methods;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class SimpleMethod implements IMethodFacade{
    private Method method;
    public SimpleMethod(Method method){
        this.method = method;
    }

    public boolean isPublic(){
        return Modifier.isPublic(method.getModifiers());
    }

    public boolean paramsCountEquals(int num){
        return (num == method.getParameterCount());
    }

    public boolean startsWith(String prefix){
        return method.getName().startsWith(prefix, 0);
    }

    public boolean isVoid(){
        return method.getReturnType().equals(Void.TYPE);
    }

    public boolean isSetter(){
        return (isPublic()
            && isVoid()
            && startsWith("set")
            && paramsCountEquals(1));
    }

    public boolean isGetter(){
        return (isPublic()
            && !isVoid()
            && (startsWith("get") || startsWith("is"))
            && paramsCountEquals(0));
    }

    public String getFieldName(){
        if(isSetter()) return method.getName().substring(3).toLowerCase();
        else if(isGetter()){
            if(startsWith("get")) return method.getName().substring(3).toLowerCase();
            return method.getName().substring(2).toLowerCase();
        }
        return null;
    }

    public Method getUnderlyingMethod(){
        return method;
    }
}
