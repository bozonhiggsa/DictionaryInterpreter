package com.english.dictionary.servlets;

import com.english.dictionary.interfaces.ProcessingWordEngFromBdService;
import com.english.dictionary.models.Counter;
import com.english.dictionary.models.WordEng;
import com.english.dictionary.models.WordRus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Controller for requests processing
 * mvc-dispatcher
 * @author Ihor Savchenko
 * @version 1.0
 */
@Controller
public class DictionaryController {

    private ProcessingWordEngFromBdService processingEngWordService;
    private static LinkedList<WordEng> wordsEngFromBd = new LinkedList<WordEng>();
    private static final Logger logger3 = LoggerFactory.getLogger(DictionaryController.class);
    private static boolean repeat;
    private static WordEng originalWordEnglish;

    @Autowired
    @Qualifier(value="processingEngWordService")
    public void setProcessingEngWordService(ProcessingWordEngFromBdService processingEngWordService) {
        this.processingEngWordService = processingEngWordService;
    }

    @RequestMapping(value = "/translate", method = RequestMethod.GET)
    public String readWord(Model model) {

        WordEng wordEng = this.processingEngWordService.selectWordEngFromBd();
        processingTemplate(wordEng, model);
        return "task";
    }

    @RequestMapping(value = "/repeat", method = RequestMethod.GET)
    public String repeatWord(Model model) {

        WordEng wordEng = this.processingEngWordService.selectWordEngForRepeat();
        processingTemplate(wordEng, model);
        repeat = true;
        return "task";
    }

    @RequestMapping(value="/verify", method = RequestMethod.POST)
    public String verifyWord(@ModelAttribute("wordEnglish") WordEng wordEngAsReply, Model model){

        logger3.warn("Retrieved english word-reply: " + wordEngAsReply.getWord());

        boolean replyReveal = false;
        WordEng wordEngForRemovingFromList = null;
        for (WordEng wordFromList: wordsEngFromBd) {
            if(wordEngAsReply.getWord().trim().equalsIgnoreCase(wordFromList.getWord())){
                this.processingEngWordService.updateWordEngFromBd(wordFromList);
                replyReveal = true;
                logger3.warn("Coincidence revealed!  English word was updated: " + wordFromList.getWord());
                wordEngForRemovingFromList = wordFromList;
            }
        }
        if(replyReveal){
            wordsEngFromBd.remove(wordEngForRemovingFromList);
            for (WordEng wordFromList: wordsEngFromBd) {
                logger3.warn("Now list has comprised words: " + wordFromList.getWord());
            }
        } else {
            Set<WordRus> setWordRus = originalWordEnglish.getWordsRus();
            model.addAttribute("setWordRussian", setWordRus);
            model.addAttribute("check", false);
            return "pageReturn";
        }

        return choosePageForRendering(model);
    }

    @RequestMapping(value="/well_known", method = RequestMethod.GET)
    public String omitWord(Model model){

        WordEng wordEngForOmit = originalWordEnglish;
        this.processingEngWordService.updateWordEngFromBd(wordEngForOmit);
        logger3.warn("Удаляем известное слово: " + wordEngForOmit.getWord());
        wordsEngFromBd.remove(wordEngForOmit);
        for (WordEng wordFromList: wordsEngFromBd) {
            logger3.warn("Now list has comprised word: " + wordFromList.getWord());
        }

        return choosePageForRendering(model);
    }

    @RequestMapping(value="/resetCount", method = RequestMethod.GET)
    public String resetCount(Model model){

        if(wordsEngFromBd.isEmpty()){
            this.processingEngWordService.resetMark();
            return "redirect: translate";
        }
        Set<WordRus> setWordRus = wordsEngFromBd.getFirst().getWordsRus();
        model.addAttribute("setWordRussian", setWordRus);
        model.addAttribute("wordEnglishList", wordsEngFromBd);
        this.processingEngWordService.resetMark();
        Counter counter = new Counter();
        int countALL = this.processingEngWordService.selectCountAll();
        counter.setCountALL(countALL);
        int countRemain = this.processingEngWordService.selectCountRemain();
        counter.setCountRemain(countRemain);
        int countForDone = this.processingEngWordService.selectCountForDone();
        counter.setCountForDone(countForDone);
        model.addAttribute("counterView", counter);
        return "task";
    }

    @RequestMapping(value="/prompt", method = RequestMethod.GET)
    public String prompt(Model model){

        WordEng wordEng = originalWordEnglish;
        Set<WordRus> setWordRus = wordEng.getWordsRus();
        model.addAttribute("setWordRussian", setWordRus);
        model.addAttribute("originalWordEnglish", wordEng);
        model.addAttribute("check", true);

        return "pageReturn";
    }

    private void processingTemplate(WordEng wordEng, Model model){
        LinkedList<WordEng> wordEngList = this.processingEngWordService.selectListWordEngFromBd(wordEng);
        Set<WordRus> setWordRus = new LinkedHashSet<WordRus>();
        if(!wordEngList.isEmpty()){
            logger3.warn("English word in servlet's body: " + wordEng.getWord());
            logger3.warn("List of associated English words: " + wordEngList.toString());
            setWordRus = wordEng.getWordsRus();
            logger3.warn("List of associated Russian words: " + setWordRus.toString());
        }
        wordsEngFromBd = wordEngList;
        model.addAttribute("setWordRussian", setWordRus);
        model.addAttribute("originalWordEnglish", wordEng);
        originalWordEnglish = wordEng;
        model.addAttribute("wordEnglishList", wordEngList);
        Counter counter = new Counter();
        int countALL = this.processingEngWordService.selectCountAll();
        counter.setCountALL(countALL);
        int countRemain = this.processingEngWordService.selectCountRemain();
        counter.setCountRemain(countRemain);
        int countForDone = this.processingEngWordService.selectCountForDone();
        counter.setCountForDone(countForDone);
        model.addAttribute("counterView", counter);
    }

    private String choosePageForRendering(Model model){
        if(wordsEngFromBd.isEmpty()){
            if(this.processingEngWordService.selectCountForDone() != 0 && repeat){
                return "redirect: repeat";
            } else {
                repeat = false;
                return "redirect: translate";
            }
        } else {
            WordEng wordEng = wordsEngFromBd.getFirst();
            logger3.warn("Следующим вариантом будет: " + wordEng.getWord());
            originalWordEnglish = wordEng;
            Set<WordRus> setWordRus = wordEng.getWordsRus();
            model.addAttribute("setWordRussian", setWordRus);
            model.addAttribute("originalWordEnglish", wordEng);
            return "otherVariant";
        }
    }

}
