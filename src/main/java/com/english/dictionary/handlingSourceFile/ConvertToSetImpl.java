package com.english.dictionary.handlingSourceFile;

import com.english.dictionary.interfaces.ConvertToSet;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

/**
 * Implementation of {@link ConvertToSet} interface for converting txt file to Set of String
 */
public class ConvertToSetImpl implements ConvertToSet {

    public Set<String> createSet(BufferedReader reader) {

        TreeSet<String> set = new TreeSet<String>();

        try {
            String s;
            while ((s = reader.readLine()) != null){
                set.add(s.toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return set;
        }
    }
}
