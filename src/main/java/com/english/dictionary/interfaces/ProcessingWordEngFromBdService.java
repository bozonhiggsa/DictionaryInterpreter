package com.english.dictionary.interfaces;

import com.english.dictionary.models.WordEng;
import java.util.LinkedList;

/**
 * Service interface to process one English Word or English phrase and associated Russian Words
 * from BD
 * @author Ihor Savchenko
 * @version 1.0
 */
public interface ProcessingWordEngFromBdService {

    WordEng selectWordEngFromBd();

    WordEng selectWordEngForRepeat();

    LinkedList<WordEng> selectListWordEngFromBd(WordEng wordEng);

    void updateWordEngFromBd(WordEng wordEngFromBd);

    int selectCountAll();

    int selectCountRemain();

    int selectCountForDone();

    void resetMark();

}
