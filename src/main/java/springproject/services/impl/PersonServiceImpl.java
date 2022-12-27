package springproject.services.impl;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springproject.models.Person;
import springproject.repositories.impl.PersonRepository;
import springproject.services.PersonService;

import java.util.Collection;
import java.util.Optional;

@NoArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {
    private PersonRepository personRepository;

    public PersonRepository getPersonRepository() {
        return personRepository;
    }

    @Autowired
    public void setPersonRepository(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public Collection<Person> getAllPeople(){
        return personRepository.getAll();
    }

    public Optional<Person> getPersonByLogin(String login){
        return personRepository.getById(login);
    }

    public void createPerson(Person person){
        personRepository.create(person);
    }
    public void updatePerson(Person person){personRepository.update(person);}
    public void deletePerson(String login){
        personRepository.delete(login);
    }
}
