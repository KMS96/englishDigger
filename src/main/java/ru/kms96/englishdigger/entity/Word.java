package ru.kms96.englishdigger.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter@Setter
@Table(name = "words")
public class Word {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String englishTranslate;

    @Column
    private String russianTranslate;
}
