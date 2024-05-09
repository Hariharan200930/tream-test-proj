package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class Controller {
    
    @GetMapping("/home")
    public String home()
    {
        return "This is home";
    }

    @GetMapping("/admin")
    public String admin(){
        return "this is admin";
    }
}
