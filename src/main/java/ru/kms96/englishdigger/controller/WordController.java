package ru.kms96.englishdigger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kms96.englishdigger.entity.Word;
import ru.kms96.englishdigger.service.WordService;
import ru.kms96.englishdigger.utills.DictionaryUtills;

import java.util.List;


@RestController
@RequestMapping(value = "/words")
public class WordController {

    private final WordService wordService;

    @Autowired
    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping(value = "/{startLetter}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView getAllWordsByFirstLetter(@PathVariable("startLetter") String startLetter) {
        List<Word> englishWordsStartsWith = wordService.getAllWordsByFirstLetter(startLetter);
        ModelAndView resultView = new ModelAndView();
        if (null == englishWordsStartsWith) {
            resultView.setStatus(HttpStatus.NOT_FOUND);
            return resultView;
        }
        resultView.setViewName("/words/list");
        resultView.addObject("englishWords", englishWordsStartsWith);
        resultView.setStatus(HttpStatus.OK);

        return resultView;
    }

    @RequestMapping(value = "/add/{newWord}", method = RequestMethod.GET)
    public ModelAndView addNewWord(@PathVariable("newWord") String newWord) {
        Word addedWord = wordService.addNewWord(newWord);
        ModelAndView resultView = new ModelAndView();
        if (null == addedWord) {
            resultView.setStatus(HttpStatus.NOT_FOUND);
            return resultView;
        }
        resultView.setViewName("/words/full_list");
        resultView.addObject("englishWords", wordService.getAllWords());
        resultView.setStatus(HttpStatus.OK);

        return resultView;
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView getAllEnglishLetters() {
        ModelAndView resultView = new ModelAndView();
        resultView.setViewName("/words/english_list");
        resultView.addObject("englishLetters", DictionaryUtills.generateEnglishAlphabet());
        resultView.setStatus(HttpStatus.OK);

        return resultView;
    }
}
