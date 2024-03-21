package com.tsi.khalifa.vmo2spring.repository;
import com.tsi.khalifa.vmo2spring.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Integer> {
}
