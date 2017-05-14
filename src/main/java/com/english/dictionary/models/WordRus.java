package com.english.dictionary.models;

import javax.persistence.*;

/**
 * Base model for russian words
 * @author Ihor Savchenko
 * @version 1.0
 */
@Entity
@Table(name = "russian_words")
public class WordRus implements Comparable<WordRus> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "word", unique = true)
    private String word;

    public WordRus(String word) {
        this.word = word;
    }

    public WordRus() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int compareTo(WordRus that) {
        return this.getWord().compareTo(that.getWord());
    }
}
