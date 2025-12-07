package com.example.mamieapp.controller;


import com.example.mamieapp.Exeception.PersonNotFoundException;
import com.example.mamieapp.Person;
import com.example.mamieapp.Repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/persons")
public class PersonController {

    final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        return new ResponseEntity<>(personRepository.findAll(), HttpStatus.OK);


    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person newPerson = personRepository.save(person);
        if(person.getName().length()>2){
            return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
        } else throw new IllegalArgumentException("nom trop court");
    }
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Optional<Person> person = personRepository.findById(id);
            return person.map(value -> new ResponseEntity<>(person.get(), HttpStatus.OK)).orElseThrow(() -> new PersonNotFoundException("personne non retrouvé"));
        // sans gérer les erreurs return person.map(value -> new ResponseEntity<>(person.get(), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));


    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id,@RequestBody Person persondetails) {
        Optional<Person> person = personRepository.findById(id);
        if(person.isPresent()) {
            Person existingPerson = person.get();
            existingPerson.setName(persondetails.getName());
            existingPerson.setPhone(persondetails.getPhone());
            existingPerson.setCity(persondetails.getCity());
            return new ResponseEntity<>(personRepository.save(existingPerson), HttpStatus.OK);
        }
        throw new PersonNotFoundException("personne non retrouvé");
        // sans gérer les erreurs  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Person> deletePerson(@PathVariable Long id) {
        Optional<Person> person = personRepository.findById(id);
        if(person.isPresent()) {
            personRepository.delete(person.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        throw  new PersonNotFoundException("personne non retrouvé");
    }
}
