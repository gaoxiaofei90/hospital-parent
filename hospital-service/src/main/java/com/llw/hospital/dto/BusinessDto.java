package com.llw.hospital.dto;

import com.jcl.dto.BaseDto;
import java.io.Serializable;
import java.util.Date;

/**
 * 险种/业务
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2019-08-26 17:30:44
 */
public class BusinessDto extends BaseDto{

	        /**
         * 业务类型
         */
    private String busiType;
	        /**
         * 业务名称
         */
    private String busiName;
	
	        /**
         * 设置：业务类型
         */
        public void setBusiType(String busiType) {
            this.busiType = busiType;
        }
        /**
         * 获取：业务类型
         */
        public String getBusiType() {
            return busiType;
        }
	        /**
         * 设置：业务名称
         */
        public void setBusiName(String busiName) {
            this.busiName = busiName;
        }
        /**
         * 获取：业务名称
         */
        public String getBusiName() {
            return busiName;
        }
	}
