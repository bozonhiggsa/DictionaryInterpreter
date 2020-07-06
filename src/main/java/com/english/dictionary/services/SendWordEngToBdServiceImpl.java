package com.english.dictionary.services;

import com.english.dictionary.interfaces.SendWordEngToBd;
import com.english.dictionary.interfaces.SendWordEngToBdService;
import com.english.dictionary.models.WordEng;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link SendWordEngToBdService} service interface for send one English Word
 * or English phrase and associated Russian Words from the dictionary into BD
 * @author Ihor Savchenko
 * @version 1.0
 */
@Service
public class SendWordEngToBdServiceImpl implements SendWordEngToBdService {

    private SendWordEngToBd sendEngWord;

    public void setSendEngWord(SendWordEngToBd sendEngWord) {
        this.sendEngWord = sendEngWord;
    }

    @Transactional
    public void sendWordEngToBd(WordEng wordEng) {
        this.sendEngWord.sendWordEngToBd(wordEng);
    }
}
