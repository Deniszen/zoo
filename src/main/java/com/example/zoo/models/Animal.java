package com.example.zoo.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Animal {

    @Id
    private String id;

    private String type;

    private String name;

    private String age;
}
