package ru.kms96.englishdigger.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kms96.englishdigger.entity.Word;

import java.util.List;

@Repository
public interface WordRepository extends CrudRepository<Word, Long> {

    List<Word> findAllByEnglishTranslateStartsWithOrderByEnglishTranslate(String startLetter);
}
