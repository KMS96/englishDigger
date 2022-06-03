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
        ModelAndView returnedResultView = new ModelAndView();
        if (null == englishWordsStartsWith) {
            returnedResultView.setStatus(HttpStatus.NOT_FOUND);
            return returnedResultView;
        }

        returnedResultView.setViewName("/words/english_list");
        returnedResultView.addObject("englishLetters", DictionaryUtills.generateEnglishAlphabet());
        returnedResultView.addObject("englishWords", englishWordsStartsWith);
        returnedResultView.setStatus(HttpStatus.OK);
        return returnedResultView;
    }

    @GetMapping("/add")
    public ModelAndView addWordForm(ModelAndView model) {
        model.setViewName("words/add_word");
        model.addObject("word", new Word());
        model.setStatus(HttpStatus.OK);

        return model;
    }

    @PostMapping("/added")
    public ModelAndView addWordSubmit(@ModelAttribute Word word) {
        wordService.addNewWord(word);

        ModelAndView resultView = new ModelAndView();
        resultView.setViewName("words/add_word");
        resultView.addObject("word", new Word());
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

    @GetMapping("/find")
    public ModelAndView searchWords(@ModelAttribute Word searchSubstring) {
        List<Word> foundedWordsBySubstring = wordService.findWordsBySubstring(searchSubstring.getEnglishTranslate());

        ModelAndView resultView = new ModelAndView();
        resultView.setViewName("words/english_list");
        resultView.addObject("word", searchSubstring);
        resultView.addObject("englishLetters", DictionaryUtills.generateEnglishAlphabet());;
        resultView.addObject("englishWords", foundedWordsBySubstring);
        resultView.setStatus(HttpStatus.OK);

        return resultView;
    }
}
