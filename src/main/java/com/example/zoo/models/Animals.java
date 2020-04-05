package com.example.zoo.models;

import lombok.Data;

import javax.persistence.OneToMany;
import java.util.ArrayList;

@Data
public class Animals {

    private long count;

    @OneToMany
    private ArrayList<Animal> animals;

}
