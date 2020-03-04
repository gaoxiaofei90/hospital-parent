package com.llw.hospital.bs.common.model;

import com.llw.module.excel.annotation.ExcelField;
import com.llw.module.excel.annotation.ExcelSheet;
import org.apache.poi.hssf.util.HSSFColor;

import java.io.Serializable;

/**
 * 有偿服务登记导出
 */
@ExcelSheet(name = "有偿服务", headColor = HSSFColor.HSSFColorPredefined.LIGHT_GREEN)
public class ServiceExport implements Serializable {
    @ExcelField(name = "入院编号")
    private String admissionNo;

    @ExcelField(name = "长者姓名")
    private String elderName;

    @ExcelField(name = "服务类型")
    private String serviceTypeName;

    @ExcelField(name = "服务项目")
    private String serviceItemName;

    @ExcelField(name = "项目费用")
    private String servicePrice;

    @ExcelField(name = "服务频次")
    private String serviceFrequencyName;

    @ExcelField(name = "开始日期")
    private String startDaysLabel;

    @ExcelField(name = "结束日期")
    private String endDaysLabel;

    @ExcelField(name = "费用总金额")
    private String total;

    @ExcelField(name = "状态")
    private String feeStatusLabel;

    @ExcelField(name = "登记人")
    private String recorderUser;

    public String getAdmissionNo() {
        return admissionNo;
    }

    public void setAdmissionNo(String admissionNo) {
        this.admissionNo = admissionNo;
    }

    public String getElderName() {
        return elderName;
    }

    public void setElderName(String elderName) {
        this.elderName = elderName;
    }

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }

    public String getServiceItemName() {
        return serviceItemName;
    }

    public void setServiceItemName(String serviceItemName) {
        this.serviceItemName = serviceItemName;
    }

    public String getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(String servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getServiceFrequencyName() {
        return serviceFrequencyName;
    }

    public void setServiceFrequencyName(String serviceFrequencyName) {
        this.serviceFrequencyName = serviceFrequencyName;
    }

    public String getStartDaysLabel() {
        return startDaysLabel;
    }

    public void setStartDaysLabel(String startDaysLabel) {
        this.startDaysLabel = startDaysLabel;
    }

    public String getEndDaysLabel() {
        return endDaysLabel;
    }

    public void setEndDaysLabel(String endDaysLabel) {
        this.endDaysLabel = endDaysLabel;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getFeeStatusLabel() {
        return feeStatusLabel;
    }

    public void setFeeStatusLabel(String feeStatusLabel) {
        this.feeStatusLabel = feeStatusLabel;
    }

    public String getRecorderUser() {
        return recorderUser;
    }

    public void setRecorderUser(String recorderUser) {
        this.recorderUser = recorderUser;
    }
}
