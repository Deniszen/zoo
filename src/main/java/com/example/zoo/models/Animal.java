package com.example.zoo.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Animal {

    @Id
    private long id;

    private String type;

    private String name;

    private String age;

    @CreationTimestamp
    private LocalDateTime time;
}
