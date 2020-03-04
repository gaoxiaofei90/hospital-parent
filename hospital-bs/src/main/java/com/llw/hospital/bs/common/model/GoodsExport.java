package com.llw.hospital.bs.common.model;

import com.llw.module.excel.annotation.ExcelField;
import com.llw.module.excel.annotation.ExcelSheet;
import org.apache.poi.hssf.util.HSSFColor;

import java.io.Serializable;

/**
 * 长者物品导出
 */
@ExcelSheet(name = "长者物品", headColor = HSSFColor.HSSFColorPredefined.LIGHT_GREEN)
public class GoodsExport implements Serializable {
    @ExcelField(name = "长者姓名")
    private String elderName;

    @ExcelField(name = "物品编号")
    private String goodsNo;

    @ExcelField(name = "物品名称")
    private String goodsName;

    @ExcelField(name = "物品数量")
    private String goodsCount;

    @ExcelField(name = "存放区")
    private String position;

    @ExcelField(name = "存放时间")
    private String depositTimeLabel;

    @ExcelField(name = "状态")
    private String goodsStatusLabel;

    @ExcelField(name = "领用人")
    private String receiverUserLabel;

    @ExcelField(name = "领用时间")
    private String receiveDateLabel;

    @ExcelField(name = "登记人")
    private String recorderUserName;

    public String getElderName() {
        return elderName;
    }

    public void setElderName(String elderName) {
        this.elderName = elderName;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(String goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepositTimeLabel() {
        return depositTimeLabel;
    }

    public void setDepositTimeLabel(String depositTimeLabel) {
        this.depositTimeLabel = depositTimeLabel;
    }

    public String getGoodsStatusLabel() {
        return goodsStatusLabel;
    }

    public void setGoodsStatusLabel(String goodsStatusLabel) {
        this.goodsStatusLabel = goodsStatusLabel;
    }

    public String getReceiverUserLabel() {
        return receiverUserLabel;
    }

    public void setReceiverUserLabel(String receiverUserLabel) {
        this.receiverUserLabel = receiverUserLabel;
    }

    public String getReceiveDateLabel() {
        return receiveDateLabel;
    }

    public void setReceiveDateLabel(String receiveDateLabel) {
        this.receiveDateLabel = receiveDateLabel;
    }


    public String getRecorderUserName() {
        return recorderUserName;
    }

    public void setRecorderUserName(String recorderUserName) {
        this.recorderUserName = recorderUserName;
    }
}
