package com.english.dictionary.interfaces;

import java.util.TreeSet;

/**
 * Interface for extracting words from String
 * @author Ihor Savchenko
 * @version 1.0
 */
public interface ExtractWords {

    TreeSet<String> extractWords(String s);

}
