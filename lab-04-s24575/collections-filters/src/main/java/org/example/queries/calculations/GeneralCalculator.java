package org.example.queries.calculations;

import java.util.function.*;

import org.example.model.Person;
import org.example.queries.search.FunctionsParameters;

import java.lang.Number;
import java.util.List;

public class GeneralCalculator implements ICalculate {
    private String operation;
    private Function<Person, Number> function;

    public GeneralCalculator(String operation, Function<Person, Number> function){
        this.operation = operation;
        this.function = function;
    }

    public double calculate(FunctionsParameters parameters, List<Person> items){
        if(parameters.getFieldName() != operation) return -1.0;

        double result = 0.0;

        switch(parameters.getFunction()){
            case AVERAGE:
            for(var person : items){
                result += function.apply(person).doubleValue();
            }
            result /= items.size();
            break;
            case MAX:
            for(var person : items){
                result = Math.max(function.apply(person).doubleValue(), result);
            }
            break;
            case MIN:
            for(var person : items){
                result = Math.min(function.apply(person).doubleValue(), result);
            }
            break;
            case SUM:
            for(var person : items){
                result += function.apply(person).doubleValue();
            }
            break;
        }

        return result;
    }

    public String getOperation(){ return operation; }
}
