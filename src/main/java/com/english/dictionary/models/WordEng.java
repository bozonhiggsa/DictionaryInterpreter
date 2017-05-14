package com.english.dictionary.models;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * Base model for english words
 * @author Ihor Savchenko
 * @version 1.0
 */
@Entity
@Table(name = "english_words")
public class WordEng {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "word", unique = true)
    private String word;

    @Column(name = "mark")
    private boolean mark;

    @Column(name = "done")
    private boolean done;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "third_table", joinColumns = @JoinColumn(name = "id_eng"),
    inverseJoinColumns = @JoinColumn(name = "id_rus"))
    private Set<WordRus> wordsRus;

    public WordEng(String word) {
        this.word = word;
    }

    public WordEng() {
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

    public boolean isMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Set<WordRus> getWordsRus() {
        return wordsRus;
    }

    public void setWordsRus(TreeSet<WordRus> wordsRus) {
        this.wordsRus = wordsRus;
    }

}
