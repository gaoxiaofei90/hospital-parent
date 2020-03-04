package com.llw.hospital.dto;

import java.io.Serializable;

public class PersonScore implements Serializable {
    public PersonScore(){

    }

    public PersonScore(Long personId, Float score) {
        this.personId = personId;
        this.score = score;
    }

    Long personId;
    Float score;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }
}
