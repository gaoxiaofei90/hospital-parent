package com.llw.hospital.vo.medical;

import java.io.Serializable;

public class InHospitalPersonVo implements Serializable {
    private Long personId;
    private Long orgId;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}
