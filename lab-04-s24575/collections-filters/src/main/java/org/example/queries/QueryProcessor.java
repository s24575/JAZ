package org.example.queries;

import org.example.model.Person;
import org.example.queries.filters.IFilterPeople;
import org.example.queries.calculations.ICalculate;
import org.example.queries.pages.ICutToPage;
import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;
import org.example.queries.search.Page;

import java.util.List;
import java.util.ArrayList;

public class QueryProcessor {
    List<IFilterPeople> filters = new ArrayList<>();
    List<ICalculate> calculators = new ArrayList<>();
    ICutToPage pageCutter;

    public Results GetResults(SearchParameters parameters, List<Person> data){
        Results results = new Results();

        // filter data
        for(var filter : filters){
            filter.setSearchParameters(parameters);
            if(filter.canFilter()){
                data = filter.filter(data);
            }
        }
        results.setItems(data);

        // use functions
        List<FunctionResult> functionResults = new ArrayList<>();
        for(var function : parameters.getFunctions()){
            for(var calculator : calculators){
                double result = calculator.calculate(function, data);
                if(result != -1.0){
                    FunctionResult functionResult = new FunctionResult();
                    functionResult.setFieldName(function.getFieldName());
                    functionResult.setFunction(function.getFunction());
                    functionResult.setValue(result);
                    functionResults.add(functionResult);
                }
            }
        }
        results.setFunctionResults(functionResults);

        // cut page
        if(pageCutter != null){
            Page page = parameters.getPage();
            results.setPages((int)Math.ceil((double)data.size() / (double)page.getSize()));
            data = pageCutter.cut(page, data);
            results.setCurrentPage((int)Math.ceil((double)data.size() / (double)page.getSize()));
        }
        
        return results;
    }

    public QueryProcessor addFilter(IFilterPeople filter){
        filters.add(filter);
        return this;
    }

    public QueryProcessor addCalculation(ICalculate calculator){
        calculators.add(calculator);
        return this;
    }

    public void addPageCutter(ICutToPage pageCutter){
        this.pageCutter = pageCutter;
    }
}
