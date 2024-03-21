package com.tsi.khalifa.vmo2spring.entity;

import jakarta.persistence.*;

@Entity
@Table(name="language")
public class Language {
    @Id
    @Column(name = "language_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int languageId;
    @Column(name = "name")

    private String name;

    public Language() {}

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
