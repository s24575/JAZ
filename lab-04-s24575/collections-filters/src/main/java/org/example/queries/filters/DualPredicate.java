package org.example.queries.filters;

import org.example.queries.search.SearchParameters;
import org.example.model.Person;

@FunctionalInterface
public interface DualPredicate {
    public boolean check(SearchParameters searchParameters, Person person);
}
