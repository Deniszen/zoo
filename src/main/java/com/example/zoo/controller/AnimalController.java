package com.example.zoo.controller;

import com.example.zoo.exceptions.AnimalConflictException;
import com.example.zoo.exceptions.AnimalNotFoundException;
import com.example.zoo.models.Animal;
import com.example.zoo.repository.AnimalRepository;
import lombok.var;
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
    public Animal getAnimal(@PathVariable String id) {
        return repository.findById(id).orElseThrow(AnimalNotFoundException::new);
    }

    @GetMapping(value="/list", produces = "application/json")
    public ResponseEntity<Iterable<Animal>> getAnimals() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
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
    public ResponseEntity deleteAnimal(@PathVariable String id) {
        var a = repository.findById(id).orElse(null);
        repository.findById(id).ifPresent(animal -> repository.delete(animal));
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(a.getId(), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public Animal updateAnimal(@PathVariable String id, @RequestBody Animal animal) {
        repository.findById(id).orElseThrow(AnimalNotFoundException::new);
        return repository.save(animal);
    }
}
