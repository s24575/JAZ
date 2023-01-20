package org.example;

import org.example.model.Gender;
import org.example.model.Person;
import org.example.model.Samples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PeopleAggregator {

    public Map<Gender, List<Person>> aggregatePeopleByGender(){
        List<Person> males = new ArrayList<>();
        List<Person> females = new ArrayList<>();
        Map<Gender, List<Person>> aggregatedByGender = new HashMap<>();
        aggregatedByGender.put(Gender.FEMALE, females);
        aggregatedByGender.put(Gender.MALE, males);
        for (Person person : Samples.getSampleListOfPeople()) {
            if (person.getGender() == Gender.MALE)
                aggregatedByGender.get(Gender.MALE).add(person);
            if(person.getGender() == Gender.FEMALE)
                aggregatedByGender.get(Gender.FEMALE).add(person);
        }

//        System.out.println("Males:");
//        for(Person person : aggregatedByGender.get(Gender.MALE)){
//            System.out.println(person.getName());
//        }
//        System.out.println("Females:");
//        for(Person person : aggregatedByGender.get(Gender.FEMALE)){
//            System.out.println(person.getName());
//        }

        return aggregatedByGender;
    }
}
