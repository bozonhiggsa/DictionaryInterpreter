package com.english.dictionary.dao;

import com.english.dictionary.handlingSourceFile.ExtractWordsImpl;
import com.english.dictionary.interfaces.SendDictionaryToBd;
import com.english.dictionary.interfaces.SendWordEngToBdService;
import com.english.dictionary.models.WordEng;
import com.english.dictionary.models.WordRus;
import org.springframework.context.ApplicationContext;
import java.util.Set;
import java.util.TreeSet;

/**
 * Implementation of {@link SendDictionaryToBd} for send the dictionary into BD
 * @author Ihor Savchenko
 * @version 1.0
 */
public class SendDictionaryToBdImpl implements SendDictionaryToBd {

    private Extractor instance = Extractor.getInstance();

    public void sendToBd(Set<String> setStringWithWords, ApplicationContext context){

        for (String setStrings: setStringWithWords) {

            TreeSet<String> words = new ExtractWordsImpl().extractWords(setStrings);

            WordEng wordEng = this.instance.extractWordEng(words);
            TreeSet<WordRus> wordsRus = this.instance.extractWordsRus(words, wordEng);
            wordEng.setWordsRus(wordsRus);

            SendWordEngToBdService sendWordEngToBdService = (SendWordEngToBdService) context.getBean("sendEngWordService");
            sendWordEngToBdService.sendWordEngToBd(wordEng);
        }

    }
}
