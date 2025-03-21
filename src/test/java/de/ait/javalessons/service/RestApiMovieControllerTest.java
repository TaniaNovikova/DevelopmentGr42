package de.ait.javalessons.service;

import de.ait.javalessons.model.*;
import org.junit.jupiter.api.*;
import org.springframework.http.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RestApiMovieControllerTest {
    private de.ait.javalessons.controller.RestApiMovieController restApiMovieController;

    @BeforeEach
    void setUp() {
        restApiMovieController = new de.ait.javalessons.controller.RestApiMovieController();//перед каждым тестом
        // будет заново пересоздаваться restApiMovieController
    }


    @org.junit.jupiter.api.Test
    void getAllMovies() {
        List<Movie> resultMovies = restApiMovieController.getAllMovies();

        assertFalse(resultMovies.isEmpty());
        assertEquals(5, resultMovies.size());
    }

    @org.junit.jupiter.api.Test
    void getMovieByIdFound() {
        ResponseEntity<Movie> resultFound = restApiMovieController.getMovieById(2L);
        assertEquals(HttpStatus.OK, resultFound.getStatusCode());
        assertEquals(2, resultFound.getBody().getId());
        assertEquals("Nomad land", resultFound.getBody().getTitle());
    }

    @org.junit.jupiter.api.Test
    void getMovieByIdNotFound() {
        ResponseEntity<Movie> resultNotFound = restApiMovieController.getMovieById(10L);

        assertEquals(HttpStatus.NOT_FOUND, resultNotFound.getStatusCode());
        assertEquals(null, resultNotFound.getBody());
    }

    @org.junit.jupiter.api.Test
    void addMovieAccess() {
        Movie newMovie = new Movie(6L, "Matrix", "Sci-fi", 1999);

        ResponseEntity<Movie> resultAdded = restApiMovieController.addMovie(newMovie);

        assertEquals(201, resultAdded.getStatusCode().value());
        assertEquals(6L, resultAdded.getBody().getId());
        assertEquals("Matrix", resultAdded.getBody().getTitle());
    }

    @org.junit.jupiter.api.Test
    void addMovieWithChangedId() {
        Movie newMovie = new Movie(4L, "Matrix", "Sci-fi", 1999);

        ResponseEntity<Movie> resultAdded = restApiMovieController.addMovie(newMovie);

        assertEquals(201, resultAdded.getStatusCode().value());
        assertEquals(6L, resultAdded.getBody().getId());
        assertEquals("Matrix", resultAdded.getBody().getTitle());
    }


    @org.junit.jupiter.api.Test
    void deleteMovieSuccess() {
        restApiMovieController.deleteMovie(2L);
        assertEquals(4, restApiMovieController.getAllMovies().size());
    }

    @org.junit.jupiter.api.Test
    void deleteMovieFailed() {
        restApiMovieController.deleteMovie(20L);
        assertEquals(5, restApiMovieController.getAllMovies().size());
    }
}