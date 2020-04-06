package com.example.zoo.repository;

import com.example.zoo.models.Animal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, Long> {

    ArrayList<Animal> findByOrderByIdAsc();

    ArrayList<Animal> findByOrderByIdDesc();

}
