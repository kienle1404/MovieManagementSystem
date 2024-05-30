package com.mvms.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Long filmId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "release_year", nullable = false)
    private Long releaseYear;

    @Column(name = "rental_duration", nullable = false)
    private Long rentalDuration;

    @Column(name = "rental_rate", nullable = false)
    private Long rentalRate;

    @Column(name = "length", nullable = false)
    private Long length;

    @Column(name = "replacement_cost", nullable = false)
    private Long replacementCost;

    @Column(name = "rating", nullable = false)
    private Long rating;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @Column(name = "special_features", nullable = false)
    private String specialFeatures;

    @Column(name = "fulltext", nullable = false)
    private String fulltext;

    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName = "language_id")
    private Language language;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    private List<Inventory> inventories;

    public Film(String title, String description) {
        this.title = title;
        this.description = description;
        this.releaseYear = 2024L;
        this.rentalDuration = 365L;
        this.rentalRate = 10L;
        this.length = 180L;
        this.replacementCost = 10L;
        this.rating = 5L;
        this.lastUpdate = LocalDateTime.now();
        this.specialFeatures = "";
        this.fulltext = "";
    }

    public Film(String title, String description, Long releaseYear, Long rentalDuration, Long rentalRate, Long length, Long replacementCost, Long rating, String specialFeatures, String fulltext) {
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.rentalDuration = rentalDuration;
        this.rentalRate = rentalRate;
        this.length = length;
        this.replacementCost = replacementCost;
        this.rating = rating;
        this.lastUpdate = LocalDateTime.now();
        this.specialFeatures = specialFeatures;
        this.fulltext = fulltext;
    }

    public Long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Long releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Long getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(Long rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public Long getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(Long rentalRate) {
        this.rentalRate = rentalRate;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public Long getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(Long replacementCost) {
        this.replacementCost = replacementCost;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(String specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    public String getFulltext() {
        return fulltext;
    }

    public void setFulltext(String fulltext) {
        this.fulltext = fulltext;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public List<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(List<Inventory> inventories) {
        this.inventories = inventories;
    }
}
