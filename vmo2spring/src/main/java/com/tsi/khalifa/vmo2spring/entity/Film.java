package com.tsi.khalifa.vmo2spring.entity;
import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.*;

import java.util.Optional;
import java.util.Set;
@Entity
@Table(name="film")
public class Film {
    public short getFilmId() {
        return filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

/*    public int getLanguageId() {
       return languageId;
    } */

    public void setFilmId(short filmId) {
        this.filmId = filmId;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }
    @JsonGetter("language")
    public String getLanguageName() {
        return language != null ? language.getName() : null;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }


    @Id
    @Column(name="film_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short filmId;

    @Column(name = "title")
    private String title;

    /*public void setLanguageId(int languageId) {
        this.languageId = languageId;
    } */

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private String releaseYear;

    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName = "language_id")
    private Language language;

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    @ManyToMany
    @JoinTable(name = "film_actor", joinColumns = @JoinColumn(name = "film_id"), inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private Set<Actor> actors;




    /*@Column(name = "language_id", unique = true)
    private int languageId; */

}
