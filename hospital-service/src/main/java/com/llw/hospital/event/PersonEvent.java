package com.llw.hospital.event;

import org.springframework.context.ApplicationEvent;

public class PersonEvent extends ApplicationEvent {

    private Long personId;
    public PersonEvent(Object source,Long personId) {
        super(source);
        this.personId = personId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        return "PersonEvent{" +
                "personId=" + personId +
                '}';
    }
}
