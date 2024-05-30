package com.mvms.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "film_category")
public class FilmCategory {
    @ManyToOne
    @JoinColumn(name = "film_id", referencedColumnName = "film_id")
    private Long filmId;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Long categoryId;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    public FilmCategory() {
        this.lastUpdate = LocalDateTime.now();
    }

    public Long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
