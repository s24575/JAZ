package org.example.queries.pages;

import org.example.model.Person;
import org.example.queries.search.Page;

import java.util.List;

public interface ICutToPage {
    public List<Person> cut(Page page, List<Person> items);
}
