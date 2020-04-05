package com.example.zoo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Animal with current id is not exist")
public class AnimalNotFoundException extends RuntimeException {
}
