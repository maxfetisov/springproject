package springproject.services;

import springproject.models.Person;

import java.util.Collection;
import java.util.Optional;

public interface PersonService {
    Collection<Person> getAllPeople();

    Optional<Person> getPersonByLogin(String login);

    void createPerson(Person person);
    void updatePerson(Person person);
    void deletePerson(String login);
}
