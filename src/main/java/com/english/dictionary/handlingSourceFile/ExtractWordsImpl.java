package com.english.dictionary.handlingSourceFile;

import com.english.dictionary.interfaces.ExtractWords;

import java.util.LinkedHashSet;

/**
 * Implementation of {@link ExtractWords} interface for extracting words from String
 */
public class ExtractWordsImpl implements ExtractWords {

    public LinkedHashSet<String> extractWords(String s) {

        LinkedHashSet<String> setWords = new LinkedHashSet<String>();
        int index = s.indexOf(" – "); // shorter char
        if(index == -1){
            index = s.indexOf(" - "); // longer char
        }

        if(index > 0){
            setWords.add(s.substring(0, index));
            index += 3;
        }
        while(index > 0){
            int tempIndex = s.indexOf("; ", index);
            if(tempIndex > index){
                setWords.add(s.substring(index, tempIndex));
                index = tempIndex + 2;
            }
            else{
                setWords.add(s.substring(index, s.length()));
                break;
            }
        }

        return setWords;
    }
}
