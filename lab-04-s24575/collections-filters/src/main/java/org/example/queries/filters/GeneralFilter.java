package org.example.queries.filters;

import java.util.function.Predicate;
import java.util.List;

import org.example.queries.search.SearchParameters;
import org.example.model.Person;

public class GeneralFilter implements IFilterPeople{
    private SearchParameters searchParameters;
    private Predicate<SearchParameters> canFilterPredicate;
    private DualPredicate filterPredicate;

    public GeneralFilter(Predicate<SearchParameters> canFilterPredicate, DualPredicate filterPrediacte){
        this.canFilterPredicate = canFilterPredicate;
        this.filterPredicate = filterPrediacte;
    }

    public void setSearchParameters(SearchParameters searchParameters){
        this.searchParameters = searchParameters;
    }

    public boolean canFilter(){
        return canFilterPredicate.test(searchParameters);
    }

    public List<Person> filter(List<Person> list){
        return list.stream()
            .filter(p -> filterPredicate.check(searchParameters, p))
            .toList();
    }
}
