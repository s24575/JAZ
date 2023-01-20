package org.example.queries.filters;

import org.example.queries.search.SearchParameters;
import org.example.model.Person;

import java.util.List;

public class ByNameFilter implements IFilterPeople {
    SearchParameters searchParameters;
    public void setSearchParameters(SearchParameters searchParameters){
        this.searchParameters = searchParameters;
    }
    public boolean canFilter(){
        return (searchParameters.getName() != null);
    }
    public List<Person> filter(List<Person> items){
        return items.stream()
            .filter(person -> person.getName() == searchParameters.getName())
            .toList();
    }
}
