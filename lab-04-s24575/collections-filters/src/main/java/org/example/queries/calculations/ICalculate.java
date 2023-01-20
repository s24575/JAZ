package org.example.queries.calculations;

import org.example.queries.search.FunctionsParameters;
import org.example.model.Person;

import java.util.List;

public interface ICalculate{
    public double calculate(FunctionsParameters parameters, List<Person> items);
}