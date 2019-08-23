package com.english.dictionary.dao;

import com.english.dictionary.models.WordEng;
import com.english.dictionary.models.WordRus;

import java.util.LinkedHashSet;
import java.util.TreeMap;

/**
 * Extractor WordEng and TreeSet<WordRus> from TreeSet<String> words
 * Extractor used for {@link SendDictionaryToBdImpl} class
 * @author Ihor Savchenko
 * @version 1.0
 */
public class Extractor {

    private static Extractor instance;
    private static TreeMap<WordRus, WordRus> buffer = new TreeMap<>();

    private Extractor(){}

    public static Extractor getInstance(){
        if(instance == null){
            instance = new Extractor();
        }
        return instance;
    }

    public WordEng extractWordEng(LinkedHashSet<String> words){
        String wordEng = "";
        for (String word: words) {
            wordEng = word;
            break;
        }
        return new WordEng(wordEng);
    }

    public LinkedHashSet<WordRus> extractWordsRus(LinkedHashSet<String> words, WordEng wordEng){

        words.remove(wordEng.getWord());
        LinkedHashSet<WordRus> wordsRus = new LinkedHashSet<>();
        for (String s: words) {
            WordRus tempWordRus = new WordRus(s);
            buffer.putIfAbsent(tempWordRus, tempWordRus);
            wordsRus.add(buffer.get(tempWordRus));
        }
        return wordsRus;
    }
}
