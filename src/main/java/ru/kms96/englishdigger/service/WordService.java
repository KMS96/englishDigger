package ru.kms96.englishdigger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kms96.englishdigger.entity.Word;
import ru.kms96.englishdigger.repository.WordRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class WordService {

    private final WordRepository wordRepository;

    @Autowired
    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public List<Word> getAllWordsByFirstLetter(String startLetter) {

        startLetter = startLetter.toLowerCase();
        List<Word> allWords = new ArrayList<>(wordRepository.findAllByEnglishTranslateStartsWithOrderByEnglishTranslate(startLetter));
        startLetter = startLetter.toUpperCase();
        allWords.addAll(wordRepository.findAllByEnglishTranslateStartsWithOrderByEnglishTranslate(startLetter));

        allWords.sort(Comparator.comparing(word -> word.getEnglishTranslate().toLowerCase()));
        return allWords;
    }

    @Transactional
    public Word addNewWord(Word newWord) {

        return wordRepository.save(newWord);
    }

    public List<Word> getAllWords() {

        List<Word> allWords = new ArrayList();
        Iterable<Word> words = wordRepository.findAll();
        words.forEach(allWords::add);
        return allWords;
    }
}
