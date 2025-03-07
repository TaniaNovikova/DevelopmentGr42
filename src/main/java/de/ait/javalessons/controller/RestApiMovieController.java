package de.ait.javalessons.controller;

import de.ait.javalessons.model.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@lombok.extern.slf4j.Slf4j
@RestController
@RequestMapping("/movies")
public class RestApiMovieController {
    /*1) Определите List<Movie> как временное хранилище (in-memory list),
    где будет храниться список фильмов.*/
    private List<Movie> movieList = new ArrayList<>();

    /*2) Добавьте в контроллер несколько готовых фильмов,
    чтобы при запуске приложения в списке уже было несколько записей.*/
    public RestApiMovieController() {
        movieList.addAll(
                List.of(new Movie(1L, "Parasite", "Thriller", 2019),
                        new Movie(2L, "Nomad land", "Drama", 2020),
                        new Movie(3L, "The Shape of Water", "Fantasy", 2017),
                        new Movie(4L, "Green Book", "Biography", 2018),
                        new Movie(5L, "Everything Everywhere All at Once", "Sci-Fi", 2022)
                )
        );
    }

    //3) Реализуйте следующие конечные точки (endpoints):

    //3.1 GET /movies — возвращает список всех фильмов.
    @GetMapping
    public List<Movie> getAllMovies() {
        return movieList;
    }

    //3.2 GET /movies/{id} — возвращает конкретный фильм по его id.
    //Если фильм не найден, верните сообщение об ошибке
    // или подходящий HTTP-статус (например, 404 Not Found).

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Optional<Movie> movie = movieList.stream().filter(m -> m.getId().equals(id)).findFirst();
        if (movie.isPresent()) {
            return ResponseEntity.ok(movie.get());
        } else {
            log.error("Movie with id {} not found", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /*3.3 POST /movies — добавляет новый фильм в список.
    Данные о фильме могут приходить в формате JSON.
    Верните в ответе информацию о добавленном фильме или статус 201 Created.*/
    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        boolean exists = movieList.stream().anyMatch(m -> m.getId().equals(movie.getId()));
        if (exists) {
            log.info("A movie with this id already exists: {}", movie.getId());
            movie.setId(movieList.size() + 1L);
            log.info("Movie ID has been changed to: {}", movie.getId());
            movieList.add(movie);
            // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            return ResponseEntity.status(HttpStatus.CREATED).body(movie);
        } else {
            movieList.add(movie);
            return ResponseEntity.status(HttpStatus.CREATED).body(movie);
        }

    }

    //3.4 (Опционально) DELETE /movies/{id} — удаляет фильм из списка по id.
    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable Long id) {
     Optional<Movie> movieToDelete = movieList.stream().filter(m -> m.getId().equals(id)).findFirst();
        Movie movie = movieToDelete.orElse(null);
        if (movie != null) {
            movieList.removeIf(m -> m.getId().equals(id));
            log.info("Movie with id {} has been deleted", id);
            return ResponseEntity.ok(movie);
        } else {
            log.error("Movie to delete with id {} not found", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}



