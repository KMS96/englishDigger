package ru.kms96.englishdigger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kms96.englishdigger.entity.Word;
import ru.kms96.englishdigger.repository.WordRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class WordService {

    private final WordRepository wordRepository;

    @Autowired
    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public List<Word> getAllWordsByFirstLetter(String startLetter) {
        return wordRepository.findAllByEnglishTranslateStartsWithOrderByEnglishTranslate(startLetter);
    }

    public Word addNewWord(String newWord) {
        Word word = new Word();
        word.setEnglishTranslate(newWord);
        return wordRepository.save(word);
    }

    public List<Word> getAllWords() {
        List<Word> allWords = new ArrayList();
        Iterable<Word> words = wordRepository.findAll();
        words.forEach(allWords::add);
        return allWords;
    }
}
