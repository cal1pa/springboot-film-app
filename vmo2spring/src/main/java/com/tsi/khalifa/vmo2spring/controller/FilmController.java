package com.tsi.khalifa.vmo2spring.controller;

import com.tsi.khalifa.vmo2spring.entity.Language;
import com.tsi.khalifa.vmo2spring.repository.LanguageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import com.tsi.khalifa.vmo2spring.entity.Film;
import com.tsi.khalifa.vmo2spring.repository.FilmRepository;
import org.springframework.web.client.ResourceAccessException;

import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/films")
@CrossOrigin
public class FilmController {

    @Autowired
    private FilmRepository filmRep;

    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private LanguageRepository languageRepository;

    public Language getLanguageByName(String name) {
        return languageRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Language not found with name: " + name));
    }


//    @GetMapping("/allFilms")
//    public Iterable<Film> getAllFilms() { return filmRep.findAll();}

    //fetching only n amount of films
    @GetMapping("/allFilms")//50films
    public Iterable<Film> nFilms() {
        PageRequest pageRequest = PageRequest.of(0, 20);
        Page<Film> page = filmRep.findAll(pageRequest);
        return page.getContent();
    }

    @GetMapping("/allFilms/{id}")
    public Film getOneFilm(@PathVariable("id") int film_id) {
        return filmRep.findById(film_id).orElseThrow(() -> new ResourceAccessException("Film not found"));
    }

    @PutMapping("/allFilms/{film_id}")
    public Film replaceFilm(@RequestBody Film newFilm, @PathVariable short film_id) {
        return filmRep.findById((int) film_id).map(film -> {
            film.setTitle(newFilm.getTitle());
            film.setDescription(newFilm.getDescription());
            film.setReleaseYear(newFilm.getReleaseYear());
            return filmRep.save(film);
        }).orElseGet(() -> {
            newFilm.setFilmId(film_id);
            return filmRep.save(newFilm);
        });
    }

    @PostMapping("/allFilms")
    public Film newFilm(@RequestBody Map<String, Object> payload) {
        String title = (String) payload.get("title");
        String description = (String) payload.get("description");
        String releaseYear = (String) payload.get("releaseYear");
        String languageName = (String) payload.get("language");

        Film newFilm = new Film();
        newFilm.setTitle(title);
        newFilm.setDescription(description);
        newFilm.setReleaseYear(releaseYear);

        Language language = languageRepository.findByName(languageName)
                .orElseThrow(() -> new EntityNotFoundException("Language not found with name: " + languageName));
        newFilm.setLanguage(language);

        return filmRepository.save(newFilm);
    }



    @DeleteMapping("/allFilms/{film_id}")
    public void deleteFilm(@PathVariable short film_id) {
        filmRep.deleteById((int) film_id);
    }
}
