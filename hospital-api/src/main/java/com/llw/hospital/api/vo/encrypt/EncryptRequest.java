package com.llw.hospital.api.vo.encrypt;

import com.llw.hospital.api.annotation.Encrypt;

import java.io.Serializable;

/**
 * @author wendellpeng
 * @Title: EncryptRequest
 * @ProjectName gov-parent
 * @date 2018/9/419:11
 */
public class EncryptRequest implements Serializable {
    @Encrypt
    private String idCard;

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
