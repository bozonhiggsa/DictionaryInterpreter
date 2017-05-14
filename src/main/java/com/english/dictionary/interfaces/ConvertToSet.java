package com.english.dictionary.interfaces;

import java.io.BufferedReader;
import java.util.Set;

/**
 * Interface for converting txt file to Set of String
 * @author Ihor Savchenko
 * @version 1.0
 */
public interface ConvertToSet {

    Set<String> createSet(BufferedReader reader);
}
