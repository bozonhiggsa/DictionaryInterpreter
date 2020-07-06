package com.english.dictionary.interfaces;

import java.util.LinkedHashSet;

/**
 * Interface for extracting words from String
 * @author Ihor Savchenko
 * @version 1.0
 */
public interface ExtractWords {

    LinkedHashSet<String> extractWords(String s);

}
