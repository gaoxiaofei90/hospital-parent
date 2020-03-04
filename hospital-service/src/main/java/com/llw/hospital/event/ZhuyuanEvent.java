package com.llw.hospital.event;


/**
 * 入院事件 通过BroadcastUtil.boradCast()方法发布出来
 */
public class ZhuyuanEvent extends HospitalEvent {
    private Long personId;
    public ZhuyuanEvent(Object source,Long orgId,Long personId) {
        super(source,orgId);
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
        return "ZhuyuanEvent{" +
                "personId=" + personId +
                "} " + super.toString();
    }
}
