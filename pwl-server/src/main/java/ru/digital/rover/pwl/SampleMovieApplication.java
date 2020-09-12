package ru.digital.rover.pwl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

/**
 * @author Michael Hunger
 * @author Mark Angrish
 */
@SpringBootApplication
@EnableNeo4jRepositories("ru.digital.rover.pwl.repositories")
public class SampleMovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleMovieApplication.class, args);
    }
}
