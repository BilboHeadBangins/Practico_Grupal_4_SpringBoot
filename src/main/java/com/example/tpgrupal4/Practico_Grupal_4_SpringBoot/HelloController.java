package com.example.tpgrupal4.Practico_Grupal_4_SpringBoot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author italo
 */
@RestController
public class HelloController {
    @GetMapping("/chau")
    public String hello(){
        return "Spring boot autoconfigurado!";
    }
}
