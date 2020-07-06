package com.english.dictionary.services;

import com.english.dictionary.interfaces.ProcessingWordEngFromBd;
import com.english.dictionary.interfaces.ProcessingWordEngFromBdService;
import com.english.dictionary.models.WordEng;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.LinkedList;

/**
 * Implementation of {@link ProcessingWordEngFromBdService} service interface to process one English Word
 * or English phrase and associated Russian Words from BD
 * @author Ihor Savchenko
 * @version 1.0
 */
@Service
public class ProcessingWordEngFromBdServiceImpl implements ProcessingWordEngFromBdService {

    private ProcessingWordEngFromBd processingEngWord;

    public void setProcessingEngWord(ProcessingWordEngFromBd processingEngWord) {
        this.processingEngWord = processingEngWord;
    }

    @Transactional
    public WordEng selectWordEngFromBd() {
        return this.processingEngWord.selectWordEngFromBd();
    }

    @Transactional
    public WordEng selectWordEngForRepeat() {
        return this.processingEngWord.selectWordEngForRepeat();
    }

    @Transactional
    public LinkedList<WordEng> selectListWordEngFromBd(WordEng wordEng) {
        return this.processingEngWord.selectListWordEngFromBd(wordEng);
    }

    @Transactional
    public void updateWordEngFromBd(WordEng wordEngFromBd) {
        this.processingEngWord.updateWordEngFromBd(wordEngFromBd);
    }

    @Transactional
    public int selectCountAll() {
        return this.processingEngWord.selectCountAll();
    }

    @Transactional
    public int selectCountRemain() {
        return this.processingEngWord.selectCountRemain();
    }

    @Transactional
    public int selectCountForDone() {
        return this.processingEngWord.selectCountForDone();
    }

    @Transactional
    public void resetMark() {
        this.processingEngWord.resetMark();
    }
}
