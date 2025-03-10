package de.ait.javalessons.controller;

import de.ait.javalessons.model.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.web.client.*;
import org.springframework.http.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestApiMovieControllerIT {
    @Autowired
    private TestRestTemplate restTemplate;
    private List<Movie> movieList;


    @Test
    public void testGetAllMovies() {

        ResponseEntity<Movie[]> response = restTemplate.getForEntity("/movies", Movie[].class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody().length);
      //  assertEquals(5, response.getBody().length);
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
  //      assertEquals(6, response.getBody().getId());
    }

    @Test
    public void testAddMovieWithExistingId() {

        Movie newMovie = new Movie(1L, "Interstellar", "Sci-Fi", 2014);
        ResponseEntity<Movie> response = restTemplate.postForEntity("/movies", newMovie, Movie.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
     //   assertEquals(6L, response.getBody().getId());
    }

    @Test
    public void testAddMovieWithIncorrectId() {

        Movie newMovie = new Movie(10L, "The Matrix", "Action", 1999);
        ResponseEntity<Movie> response = restTemplate.postForEntity("/movies", newMovie, Movie.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
      //  assertEquals(6L, response.getBody().getId());
    }

    @Test
    public void testDeleteMovieFailed() {
        ResponseEntity<Movie> response = restTemplate.exchange(
                "/movies/20", HttpMethod.DELETE, null, Movie.class);//ожидаем ответ 404 при запуске метода удаления
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());

    }

    @Test
    public void testDeleteMovieSuccess() {
        restTemplate.delete("/movies/1");
        ResponseEntity<Movie> responseGetForEntity = restTemplate.getForEntity("/movies/1", Movie.class);//ищем удаленный фильм в списке
        assertEquals(404, responseGetForEntity.getStatusCodeValue());//фильм удален)=>не найден

        ResponseEntity<Movie> response = restTemplate.exchange(
                "/movies/2", HttpMethod.DELETE, null, Movie.class);//ожидаем ответ 200 при запуске метода удаления
        Movie deletedMovie = response.getBody();
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(deletedMovie.getId());
        assertEquals("Nomad land", deletedMovie.getTitle());

    }
}
