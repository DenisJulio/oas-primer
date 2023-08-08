package com.denisjulio.oasprimer;

import com.denisjulio.oasprimer.api.PersonsApi;
import com.denisjulio.oasprimer.model.Person;
import com.denisjulio.oasprimer.model.PersonSubmissionData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController("/")
public class PersonRestController implements PersonsApi {

    private static final Logger log = LoggerFactory.getLogger(PersonRestController.class);

    @Override
    public ResponseEntity<List<Person>> getPersons() {
        log.info("Received request for GET /persons");
        var person = new Person()
                .id(101)
                .firstName("Denis")
                .lastName("Julio")
                .birthDate(LocalDate.of(1992, 5, 28));
        var personsList = List.of(person);
        return ResponseEntity.ok(personsList);
    }

    @Override
    public ResponseEntity<Person> postPerson(PersonSubmissionData personSubmissionData) {
        var binder = new DataBinder(personSubmissionData);
        log.info("Received request for POST /persons");
        var person = new Person()
                .id(1)
                .firstName(personSubmissionData.getFirstName())
                .lastName(personSubmissionData.getLastName())
                .birthDate(personSubmissionData.getBirthDate());
        log.info("{}",binder.getBindingResult().getModel());
        return ResponseEntity.ok(person);
    }
}
