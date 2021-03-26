package com.activity.tracker.controller;

import com.activity.tracker.models.entities.Test;
import com.activity.tracker.repository.TestRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class TestController {

    private final TestRepository testRepository;

    public TestController(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Test createTodo(@RequestBody Test test) {
        return testRepository.save(test);
    }

    @GetMapping("/")
    public Iterable<Test> getTodos() {
        return testRepository.findAll();
    }
}


