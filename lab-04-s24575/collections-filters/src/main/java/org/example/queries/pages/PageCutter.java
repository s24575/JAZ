package org.example.queries.pages;

import org.example.model.Person;
import org.example.queries.search.Page;

import java.util.List;

public class PageCutter implements ICutToPage {
    public List<Person> cut(Page page, List<Person> items){
        return items.stream()
            .skip((page.getPageNumber() - 1) * page.getSize())
            .limit(page.getSize())
            .toList();
    }
}
