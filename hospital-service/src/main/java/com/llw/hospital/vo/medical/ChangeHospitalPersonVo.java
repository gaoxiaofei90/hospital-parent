package com.llw.hospital.vo.medical;

import java.util.Date;

public class ChangeHospitalPersonVo extends InHospitalPersonVo {
    private Integer status;
    private Date inDate;
    private Date outDate;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }
}
