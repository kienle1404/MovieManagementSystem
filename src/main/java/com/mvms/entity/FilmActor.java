package com.mvms.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "film_actor")
public class FilmActor {
    @ManyToOne
    @JoinColumn(name = "film_id", referencedColumnName = "film_id")
    private Long filmId;

    @ManyToOne
    @JoinColumn(name = "actor_id", referencedColumnName = "actor_id")
    private Long actorId;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    public FilmActor() {
        this.lastUpdate = LocalDateTime.now();
    }

    public Long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    public Long getActorId() {
        return actorId;
    }

    public void setActorId(Long actorId) {
        this.actorId = actorId;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
