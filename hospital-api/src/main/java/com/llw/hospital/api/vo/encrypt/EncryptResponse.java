package com.llw.hospital.api.vo.encrypt;

import com.llw.hospital.api.annotation.Encrypt;

import java.io.Serializable;

/**
 * @author wendellpeng
 * @Title: EncryptResponse
 * @ProjectName gov-parent
 * @date 2018/9/419:12
 */
public class EncryptResponse implements Serializable {
    @Encrypt
    private String idCard;

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
