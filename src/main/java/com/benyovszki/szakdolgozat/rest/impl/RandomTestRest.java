package com.benyovszki.szakdolgozat.rest.impl;

import com.benyovszki.szakdolgozat.rest.RestPaths;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/test")
public class RandomTestRest {

    @GetMapping(path = "/token")
    public String tested() {
        return "Token auth is working";
    }

    @GetMapping("/admin")
    public String testedAdmin() {
        return "Admin is working";
    }

}
