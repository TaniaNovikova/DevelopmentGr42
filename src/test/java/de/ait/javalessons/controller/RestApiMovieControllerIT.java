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

    private RestApiMovieController restApiMovieController;

    @BeforeEach
    void setUp() {
        restApiMovieController = new RestApiMovieController();
    }

    @Test
    public void testGetAllMovies() {
        ResponseEntity<Movie[]> response = restTemplate.getForEntity("/movies", Movie[].class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody().length);
        assertEquals(5, response.getBody().length);
    }

    @Test
    public void testGetMovieById() {
        ResponseEntity<Movie> response = restTemplate.getForEntity("/movies/3", Movie.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(3L, response.getBody().getId());
        assertEquals("The Shape of Water", response.getBody().getTitle());
    }

    @Test
    public void testGetMovieByInvalidId() {
        ResponseEntity<Movie> response = restTemplate.getForEntity("/movies/10", Movie.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }



    @Test
    public void testAddMovieWithCorrectId() {
        Movie movie = new Movie(6L, "Matrix", "Sci-fi", 1999);
        ResponseEntity<Movie> response = restTemplate.postForEntity("/movies", movie, Movie.class);
        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(6L, response.getBody().getId());
    }

    @Test
    public void testAddMovieWithExistingId() {
        Movie newMovie = new Movie(1L, "Interstellar", "Sci-Fi", 2014);
        ResponseEntity<Movie> response = restTemplate.postForEntity("/movies", newMovie, Movie.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(6L, response.getBody().getId());
    }

    @Test
    public void testAddMovieWithIncorrectId() {
        Movie newMovie = new Movie(10L, "The Matrix", "Action", 1999);
        ResponseEntity<Movie> response = restTemplate.postForEntity("/movies", newMovie, Movie.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(6L, response.getBody().getId());
    }

    @Test
    public void testDeleteMovie() {
        restTemplate.delete("/movies/2");

        ResponseEntity<Movie> response = restTemplate.getForEntity("/movies/2", Movie.class);
        assertEquals(404, response.getStatusCodeValue());
    }
}
