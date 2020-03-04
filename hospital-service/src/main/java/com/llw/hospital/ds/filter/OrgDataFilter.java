package com.llw.hospital.ds.filter;

import com.alibaba.dubbo.rpc.RpcContext;
import com.jcl.orm.tkmapper.filter.OrgFilter;
import com.llw.hospital.common.base.constants.UpmsConstant;
import com.llw.hospital.common.util.StringUtils;
import com.llw.hospital.util.OrgUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrgDataFilter implements OrgFilter{

    @Override
    public List<String> getOrgFilterSql(Class<?> aClass) {
        List<String> orgSqls = new ArrayList<>(1);
        try {
            Field[] fields = aClass.getDeclaredFields();
            boolean containsOrgId = false;
            if (null != fields && fields.length > 0) {
                for (Field f : fields) {
                    if ("orgId".equalsIgnoreCase(f.getName())) {
                        containsOrgId = true;
                    }
                }
            }
            //医院用户的数据权限控制
            if (containsOrgId) {
                String orgId = getTargetOrgId();
                if (null != orgId && !"0".equals(orgId)) {
                    Long beginId = Long.parseLong(orgId);
                    Long endId = OrgUtil.getMaxLeafTheoreticalValue(beginId);
                    orgSqls.add("org_id >= " + beginId);
                    orgSqls.add("org_id < " + endId);
                }
            }

            return orgSqls;
        }catch (Exception e){
            e.printStackTrace();
            return orgSqls;
        }
    }

    public static String getTargetOrgId() {
        Map<String, String> attachments = RpcContext.getContext().getAttachments();
        String orgId = (String) attachments.get(UpmsConstant.LOGIN_USER_CHOOSED_ORG_ID);
        //TODO 校验选择的orgId只能是自己的下属机构
        if(StringUtils.isEmpty(orgId)){
            orgId = (String) attachments.get(UpmsConstant.LOGIN_USER_ORG_ID);
        }
        return orgId;
    }
}