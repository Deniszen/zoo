package com.example.zoo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("info")
public class InfoController {

    @GetMapping(produces = "text/plain")
    public ResponseEntity<String> getInfo() {
        String message = "This is simple CRUD service\nREST routes:\n\nPOST\t\t\t/animals - for create\nGET" +
                "\t\t\t\t/animals/:id or /animals/list - for read one or all animals\nPUT" +
                "\t\t\t\t/animals/:id - for update animal\nDELETE" +
                "\t\t\t/animals/:id - for delete animal\n\n" +
                "Example:\n" +
                "Request JSON:\n" +
                "{\n" +
                " \"id\": \"id\",\n" +
                " \"type\": \"type\",\n" +
                " \"name\": \"name\",\n" +
                " \"age\": \"age\"\n" +
                "}";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
