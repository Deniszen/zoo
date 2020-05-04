package com.example.zoo.repository;

import com.example.zoo.models.Animal;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, Long>, PagingAndSortingRepository<Animal, Long> {

    ArrayList<Animal> findByOrderByIdAsc();

    ArrayList<Animal> findByOrderByIdDesc();

    List<Animal> findAllByType(String type, Pageable pageable);

    List<Animal> findAllByType(String type);
}
