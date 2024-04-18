package com.example.faan.mongo.Demo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = {"http://localhost:4200", "http://10.0.2.2:8080"})
@RequiredArgsConstructor
public class DemoController {
    @PostMapping(value = "demo")
public String welcome(){

        return "Welcome from segurity";
    }
}
