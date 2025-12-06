package com.example.mamieapp.Repository;

import com.example.mamieapp.Person;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PersonRepository extends JpaRepository<Person, Long> {

}
