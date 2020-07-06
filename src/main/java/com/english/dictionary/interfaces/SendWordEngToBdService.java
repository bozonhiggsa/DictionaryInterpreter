package com.english.dictionary.interfaces;

import com.english.dictionary.models.WordEng;

/**
 * Service interface for send one English Word or English phrase and associated Russian Words
 * from the dictionary into BD
 * @author Ihor Savchenko
 * @version 1.0
 */
public interface SendWordEngToBdService {

    void sendWordEngToBd(WordEng wordEng);
}
