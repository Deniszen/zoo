package com.example.zoo.controller;

import com.example.zoo.exceptions.AnimalConflictException;
import com.example.zoo.exceptions.AnimalNotFoundException;
import com.example.zoo.models.Animal;
import com.example.zoo.models.Animals;
import com.example.zoo.repository.AnimalRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("animals")
public class AnimalController {

    private AnimalRepository repository;

    public AnimalController(AnimalRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Animal getAnimal(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(AnimalNotFoundException::new);
    }

    @GetMapping(value="/list", produces = "application/json")
    public ResponseEntity<Animals> getAnimals(@RequestParam(required = false) String sort) {
        Animals animals = new Animals();
        if (sort != null && sort.equals("desc")) {
            animals.setAnimals(repository.findByOrderByIdDesc());
        } else if (sort != null && sort.equals("asc")) {
            animals.setAnimals(repository.findByOrderByIdAsc());
        } else {
            animals.setAnimals(repository.findByOrderByIdAsc());
        }
        animals.setCount(animals.getAnimals().size());
        return new ResponseEntity<>(animals, HttpStatus.OK);
    }

    @PostMapping(produces = "application/json")
    public Animal createAnimal(@RequestBody Animal animal) {
        if(repository.findById(animal.getId()).isPresent()) {
            throw new AnimalConflictException();
        }
        return repository.save(animal);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity deleteAnimal(@PathVariable Long id) {
        if (repository.findById(id).isPresent()) {
            repository.findById(id).ifPresent(animal -> repository.delete(animal));
            return ResponseEntity.noContent().build();
        } else {
            throw new AnimalNotFoundException();
        }
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public Animal updateAnimal(@PathVariable Long id, @RequestBody Animal animal) {
        if (repository.findById(id).isPresent()) {
            animal.setTime(repository.findById(id).get().getTime());
            return repository.save(animal);
        } else {
            return repository.findById(id).orElseThrow(AnimalNotFoundException::new);
        }
    }
}
