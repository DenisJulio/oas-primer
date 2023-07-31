package com.denisjulio.oasprimer;

import com.denisjulio.oasprimer.api.PersonsApi;
import com.denisjulio.oasprimer.model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController("/")
public class PersonRestController implements PersonsApi {

    @Override
    public ResponseEntity<List<Person>> getPersons() {
        var person = new Person()
                .id(101)
                .firstName("Denis")
                .lastName("Julio")
                .birthDate(LocalDate.of(1992, 5, 28));
        var personsList = List.of(person);
        return ResponseEntity.ok(personsList);
    }
}
