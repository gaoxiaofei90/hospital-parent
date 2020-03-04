package com.llw.hospital.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author wendellpeng
 * @Title: P
 * @ProjectName gov-parent
 * @Description: 查询参数的基类
 * @date 2018/9/4 10:01
 */
@ApiModel(value = "查询需要传递的参数",description = "查询需要传递的参数")
public class SearchKeyParam extends RequestParam {
    @ApiModelProperty(value = "页码(从1开始)" ,required = false)
    private Integer pageNo;
    @ApiModelProperty(value = "此页返回数据条数" ,required = false)
    private Integer pageSize;
    @ApiModelProperty(value = "名称或编号" ,required = false)
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
}
