package org.example.classes;

import java.util.List;
import java.lang.reflect.Field;

import org.example.methods.IMethodFacade;

public interface IClassFacade {
    public List<IMethodFacade> getPublicDeclaredMethods();
    public List<IMethodFacade> getPublicGetters();
    public List<IMethodFacade> getPublicSetters();
    public List<Field> getFieldsForPublicProperties();
}