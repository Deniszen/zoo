package com.example.zoo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "The animal with the same id was created before")
public class AnimalConflictException extends RuntimeException {
}
