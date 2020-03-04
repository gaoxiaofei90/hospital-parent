package com.llw.hospital.vo.demo;

import com.jcl.orm.tkmapper.annotation.Calculated;
import com.llw.hospital.dto.SysDemoDto;

public class DemoListVO extends SysDemoDto {
    private String extendName;

    @Calculated(ognlExpression = "@com.llw.hospital.common.util.DictUtil@getDictLabel('sex',#_thisObj.sex)")
    private String sexLabel;

    private Long personId;
    private Long modelId;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getSexLabel() {
        return sexLabel;
    }

    public void setSexLabel(String sexLabel) {
        this.sexLabel = sexLabel;
    }

    public String getExtendName() {
        return extendName;
    }

    public void setExtendName(String extendName) {
        this.extendName = extendName;
    }
}
