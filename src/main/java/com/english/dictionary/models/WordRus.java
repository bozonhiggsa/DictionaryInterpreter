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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordRus wordRus = (WordRus) o;

        if (id != wordRus.id) return false;
        return word != null ? word.equals(wordRus.word) : wordRus.word == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (word != null ? word.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WordRus{" +
                "word='" + word + '\'' +
                ", id=" + id +
                '}';
    }

    public int compareTo(WordRus that) {
        if(this.word.compareTo(that.getWord()) > 0){
            return 1;
        }
        else if(this.word.compareTo(that.getWord()) < 0){
            return -1;
        }
        else return 0;
    }

}
