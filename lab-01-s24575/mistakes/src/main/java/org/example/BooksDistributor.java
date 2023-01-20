package org.example;

import org.example.model.Person;
import org.example.model.Samples;

import java.util.Random;

public class BooksDistributor {

    public void distributeBooksThroughPeople(){

        for (Person person: Samples.getSampleListOfPeople())
        {
            if(Samples.getAvailableBooks().size() == 0) break;
            int index = getRandomIndex();
            //System.out.println(index);
            Samples.getAvailableBooks().get(index).setOwner(person);
            person.getBooks().add(Samples.getAvailableBooks().get(index));
            Samples.getAvailableBooks().remove(index);
        }

    }
    private int getRandomIndex(){
        return new Random().nextInt(Samples.getAvailableBooks().size());
    }


}







