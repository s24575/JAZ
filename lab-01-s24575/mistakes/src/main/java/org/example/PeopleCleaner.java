package org.example;

import org.example.model.Book;
import org.example.model.Person;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class PeopleCleaner {

    public List<Person> getPeopleWithBooksOnly(List<Person> people){
        List<Person> test = new ArrayList<>();
//        System.out.println(test.size());
        for (Person person : people) {
//            System.out.println(person.getName() + " " + person.getBooks().size());
            if (!person.getBooks().isEmpty()){
                test.add(person);
//                test.remove(person);
//                System.out.println("removed" + " " + person.getName());
            }
        }
//        System.out.println(test.size());
        return test;
    }
}
