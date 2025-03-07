package de.ait.javalessons.controller;

import de.ait.javalessons.model.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestApiMovieControllerIT {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAllMovies() {
        ResponseEntity<Movie[]> response = restTemplate.getForEntity("/movies", Movie[].class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    public void testAddMovie() {
        Movie movie = new Movie(5L, "Titanic", "Romance", 1997);
        ResponseEntity<Movie> response = restTemplate.postForEntity("/movies", movie, Movie.class);
        assertEquals(201, response.getStatusCodeValue());

        ResponseEntity<Movie[]> updatedList = restTemplate.getForEntity("/movies", Movie[].class);
        assertTrue(updatedList.getBody().length > 3);
    }

    @Test
    public void testDeleteMovie() {
        restTemplate.delete("/movies/2");

        ResponseEntity<Movie> response = restTemplate.getForEntity("/movies/2", Movie.class);
        assertEquals(404, response.getStatusCodeValue());
    }
}
