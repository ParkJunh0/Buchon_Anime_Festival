package com.biaf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.python.core.PyObject;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "words")
@Getter
@Setter
public class Words {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "words_seq")
	@SequenceGenerator(name = "words_seq", sequenceName = "words_seq", allocationSize = 1)
    @Column(name = "word_id")
    private Long id;

    private int counts;

    private String searchstr;

    public void create(PyObject pyArr){
        this.counts = 1;
        this.searchstr = pyArr.toString();
    }
}
