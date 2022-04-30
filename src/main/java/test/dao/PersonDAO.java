package test.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import test.models.Person;

import javax.persistence.*;
import java.util.List;

@Component
@Transactional
public class PersonDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Person> index() {
        Query query = entityManager.createQuery("select e from Person e", Person.class);
        return (List<Person>)query.getResultList();
    }

    public Person show(long id) {
        return entityManager.find(Person.class, id);
    }

    public void save(Person person) {
        entityManager.persist(person);
    }

    public void update(long id, Person updatePerson) {
        Person person = entityManager.find(Person.class, id);
        person.setEmail(updatePerson.getEmail());
        person.setAge(updatePerson.getAge());
        person.setName(updatePerson.getName());
        entityManager.merge(person);

    }

    public void delete(long id) {
        entityManager.remove(entityManager.find(Person.class, id));
    }

}
