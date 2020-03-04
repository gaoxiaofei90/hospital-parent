package com.llw.hospital.event;

import org.springframework.context.ApplicationEvent;

public class HospitalEvent extends ApplicationEvent {
    private Long orgId;
    public HospitalEvent(Object source,Long orgId) {
        super(source);
        this.orgId = orgId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    @Override
    public String toString() {
        return "HospitalEvent{" +
                "orgId=" + orgId +
                '}';
    }
}
