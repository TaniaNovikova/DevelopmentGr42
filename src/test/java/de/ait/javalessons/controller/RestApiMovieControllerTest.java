package de.ait.javalessons.controller;

import de.ait.javalessons.model.*;
import org.junit.jupiter.api.*;
import org.springframework.http.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RestApiMovieControllerTest {
    private RestApiMovieController restApiMovieController;

    @BeforeEach
    void setUp() {
        restApiMovieController = new RestApiMovieController();//перед каждым тестом
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
        assertEquals("Matrix",  resultAdded.getBody().getTitle());
    }
    @org.junit.jupiter.api.Test
    void addMovieWithChangedId() {
        Movie newMovie = new Movie(4L, "Matrix", "Sci-fi", 1999);

        ResponseEntity<Movie> resultAdded = restApiMovieController.addMovie(newMovie);

        assertEquals(201, resultAdded.getStatusCode().value());
        assertEquals(6L, resultAdded.getBody().getId());
        assertEquals("Matrix",  resultAdded.getBody().getTitle());
    }


    @org.junit.jupiter.api.Test
    void deleteMovieSuccess() {
        ResponseEntity<Movie> movieToDelete = restApiMovieController.deleteMovie(2L);
        assertEquals(HttpStatus.OK, movieToDelete.getStatusCode());
        assertEquals(2, movieToDelete.getBody().getId());
        assertEquals("Nomad land", movieToDelete.getBody().getTitle());
        assertEquals(4, restApiMovieController.getAllMovies().size());
    }

    @org.junit.jupiter.api.Test
    void deleteMovieFailed() {
        ResponseEntity<Movie> movieToDeleteWithIdNotFound = restApiMovieController.deleteMovie(20L);
        assertEquals(HttpStatus.NOT_FOUND, movieToDeleteWithIdNotFound.getStatusCode());
        assertEquals(null, movieToDeleteWithIdNotFound.getBody());
        assertEquals(5, restApiMovieController.getAllMovies().size());

    }
}