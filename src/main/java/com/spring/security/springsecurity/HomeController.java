package com.spring.security.springsecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class HomeController {

    @GetMapping("/")
    public String Home () {
        return ("<h1>Welcome<h2>");
    }

    @GetMapping("/user")
    public String user () {
        return("<h1>Welcome user<h2>");
    }

    @GetMapping("/admin")
    public String admin () {
        return("<h1>Welcome admin<h2>");
    }




}
