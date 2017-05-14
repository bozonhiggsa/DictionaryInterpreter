package com.english.dictionary.dao;

import com.english.dictionary.models.WordEng;
import com.english.dictionary.models.WordRus;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Extractor WordEng and TreeSet<WordRus> from TreeSet<String> words
 * Extractor used for {@link SendDictionaryToBdImpl} class
 * @author Ihor Savchenko
 * @version 1.0
 */
public class Extractor {

    private static Extractor instance;
    private static TreeMap<WordRus, WordRus> buffer = new TreeMap<WordRus, WordRus>();

    private Extractor(){}

    public static Extractor getInstance(){
        if(instance == null){
            instance = new Extractor();
        }
        return instance;
    }

    public WordEng extractWordEng(TreeSet<String> words){
        String wordEng = "";
        for (String word: words) {
            wordEng = word;
            break;
        }
        return new WordEng(wordEng);
    }

    public TreeSet<WordRus> extractWordsRus(TreeSet<String> words, WordEng wordEng){

        words.remove(wordEng.getWord());
        TreeSet<WordRus> wordsRus = new TreeSet<WordRus>();
        for (String s: words) {
            WordRus tempWordRus = new WordRus(s);
            buffer.putIfAbsent(tempWordRus, tempWordRus);
            wordsRus.add(buffer.get(tempWordRus));
        }
        return wordsRus;
    }
}
