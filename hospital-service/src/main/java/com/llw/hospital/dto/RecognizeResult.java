package com.llw.hospital.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class RecognizeResult implements Serializable {
    private boolean success;

    private Float score;

    private Long personId;//分数最高的人员id

    private Long pictureId;//分数最高的那张图片的标示

    //所有相似人员id
    private List<PersonScore> persons;

    private int n;

    private String message;

    public static RecognizeResult fail(String message) {
        RecognizeResult recognizeResult = new RecognizeResult();
        recognizeResult.setSuccess(false);
        recognizeResult.setMessage(message);
        return recognizeResult;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PersonScore> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonScore> persons) {
        this.persons = persons;
    }

    public Long getPictureId() {
        return pictureId;
    }

    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }
}
