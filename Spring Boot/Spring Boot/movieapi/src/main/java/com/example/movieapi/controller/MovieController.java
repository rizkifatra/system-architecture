package com.example.movieapi.controller;

import com.example.movieapi.model.Movie;
import com.example.movieapi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/schema")
    public ResponseEntity<String> getSchema() {
        try {
            List<Movie> movies = movieService.getAllMovies();
            if (!movies.isEmpty()) {
                Movie movie = movies.get(0);
                return ResponseEntity.ok("Schema seems correct. Sample movie: " + movie.toString());
            } else {
                return ResponseEntity.ok("No movies found, but schema should be correct.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error checking schema: " + e.getMessage());
        }
    }

    @GetMapping("/raw/{id}")
    public ResponseEntity<String> getRawMovieData(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);
        if (movie != null) {
            String rawData = String.format(
                    "ID: %d\nTitle: %s\nRelease Year: %d\nGenres: %s\nIMDB Rating: %.1f\n" +
                            "Length: %d min\nPoster: %s\nUpdated At: %s",
                    movie.getId(), movie.getTitle(), movie.getReleaseYear(), movie.getGenres(),
                    movie.getImdbRating(), movie.getLengthInMin(), movie.getPoster(),
                    movie.getUpdatedAt() != null ? movie.getUpdatedAt().toString() : "null"
            );
            return ResponseEntity.ok(rawData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Test endpoint working");
    }

    @GetMapping("/search")
    public List<Movie> searchMovies(@RequestParam(required = false) String title,
                                    @RequestParam(required = false) Long id,
                                    @RequestParam(required = false) String genre) {
        if (title != null) {
            return movieService.searchMoviesByTitle(title);
        } else if (id != null) {
            Movie movie = movieService.getMovieById(id);
            return movie != null ? Collections.singletonList(movie) : Collections.emptyList();
        } else if (genre != null) {
            return movieService.searchMoviesByGenre(genre);
        }
        return Collections.emptyList();
    }

    @GetMapping("/recent")
    public List<Movie> getRecentMovies() {
        Instant fiveMinutesAgo = Instant.now().minus(5, ChronoUnit.MINUTES);
        return movieService.getRecentMovies(fiveMinutesAgo);
    }

    @GetMapping("/check/{id}")
    public ResponseEntity<String> checkMovie(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);
        if (movie != null) {
            return ResponseEntity.ok("Movie: " + movie.getTitle() + ", Updated At: " + movie.getUpdatedAt());
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);
        if (movie != null) {
            return ResponseEntity.ok(movie);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        Movie newMovie = movieService.addMovie(movie);
        return ResponseEntity.ok(newMovie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movieDetails) {
        Movie updatedMovie = movieService.updateMovie(id, movieDetails);
        if (updatedMovie != null) {
            return ResponseEntity.ok(updatedMovie);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }
}
