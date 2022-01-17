package com.bdweb.frumeterapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.bdweb.frumeterapi.repository"})
@SpringBootApplication
public class FrumeterApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrumeterApiApplication.class, args);
    }

}
