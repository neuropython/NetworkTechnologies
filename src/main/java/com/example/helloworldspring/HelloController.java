package com.example.helloworldspring;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/helloWorld")
    public String hello(){
        return "Hello World!";
    }

    @GetMapping("/helloSomeone")
    public String helloSomeone(@RequestParam String name,
                               @RequestParam String surname){
        return "Hello " + name + " " + surname + "!";
    }
    @GetMapping("/anotherHello/{name}")
    public String otherHello(@PathVariable String name) {
        return "Hello " + name + "!";
    }
    @GetMapping("/add")
    public String add(@RequestParam Integer number1,
                      @RequestParam Integer number2) {
         int sum = number2 + number1;
         return "" + sum;
    }
}