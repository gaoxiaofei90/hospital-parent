package com.llw.hospital.api.vo;

import io.swagger.annotations.ApiModel;

/**
 * @author wendellpeng
 * @Title: BasePageRequest
 * @ProjectName gov-parent
 * @date 2018/9/513:58
 */
@ApiModel
public class BasePageRequest extends BaseRequestVo {
    private int pageNo = 1;
    private int pageSize = 10;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
