package com.tsi.khalifa.vmo2spring.repository;

import com.tsi.khalifa.vmo2spring.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
    Optional<Language> findByName(String name);
}
