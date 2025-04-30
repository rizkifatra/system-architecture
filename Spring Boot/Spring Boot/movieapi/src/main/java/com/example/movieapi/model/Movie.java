package com.example.movieapi.model;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "release_year")
    private Integer releaseYear;

    private String genres;

    @Column(name = "imdb_rating")
    private Float imdbRating;

    @Column(name = "length_in_min")
    private Integer lengthInMin;

    private String poster;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }

    // Constructors (if you have any)

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public String getGenres() {
        return genres;
    }

    public Float getImdbRating() {
        return imdbRating;
    }

    public Integer getLengthInMin() {
        return lengthInMin;
    }

    public String getPoster() {
        return poster;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public void setImdbRating(Float imdbRating) {
        this.imdbRating = imdbRating;
    }

    public void setLengthInMin(Integer lengthInMin) {
        this.lengthInMin = lengthInMin;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    // toString method (optional but useful for debugging)
    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseYear=" + releaseYear +
                ", genres='" + genres + '\'' +
                ", imdbRating=" + imdbRating +
                ", lengthInMin=" + lengthInMin +
                ", poster='" + poster + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
