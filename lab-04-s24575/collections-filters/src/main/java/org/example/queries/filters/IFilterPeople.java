package org.example.queries.filters;

import org.example.queries.search.SearchParameters;
import org.example.model.Person;

import java.util.List;

public interface IFilterPeople {
    public abstract void setSearchParameters(SearchParameters searchParameters);
    public abstract boolean canFilter();
    public abstract List<Person> filter(List<Person> items);
}
