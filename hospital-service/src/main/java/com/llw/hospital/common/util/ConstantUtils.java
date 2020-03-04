package com.llw.hospital.common.util;

/**
 * 常量类
 * @author heguofu
 *
 */
public class ConstantUtils {

    /**
     * 资产类型
     */
    public static enum assetType {
        JIGOU(1, "机构"), LOUDONG(2, "楼栋"),DANYUAN(3, "单元")
        ,LOUCENG(4, "楼层"),FANGJIAN(5, "房间"),CHUANGWEI(6, "床位");
        private final Integer value;
        private final String name;
        private assetType(Integer value, String name) {this.value = value; this.name = name;}
        public Integer getValue() {return value;}
        public String getName() {return name;}
    }

    /**
     *  资产状态  0 可用 1 预约 2 已用
     */
    public static enum assetStatus {
        KEYONG(0, "可用"), YUYUE(1, "预约"),YIYONG(2, "已用");
        private final Integer value;
        private final String name;
        private assetStatus(Integer value, String name) {this.value = value; this.name = name;}
        public Integer getValue() {return value;}
        public String getName() {return name;}
    }
    /**
     *  外出状态  1 已申请 2 已销假
     */
    public static enum outingStatus {
        YISHENGQING(1, "已申请"),YIXIAOJIA(2, "已销假");
        private final Integer value;
        private final String name;
        private outingStatus(Integer value, String name) {this.value = value; this.name = name;}
        public Integer getValue() {return value;}
        public String getName() {return name;}
    }

    /**
     *  费用状态  1 待结算 3 已结算
     */
    public static enum feeStatus {
        DAIJIESUAN(1, "待结算"),YIJIESUAN(3, "已结算");
        private final Integer value;
        private final String name;
        private feeStatus(Integer value, String name) {this.value = value; this.name = name;}
        public Integer getValue() {return value;}
        public String getName() {return name;}
    }

    /**
     *  是否删除 1 是 0 否 默认0
     */
    public static enum isDel {
        YES(1, "是"),NO(0, "否");
        private final Integer value;
        private final String name;
        private isDel(Integer value, String name) {this.value = value; this.name = name;}
        public Integer getValue() {return value;}
        public String getName() {return name;}
    }

    /**
     *  费用单位
     */
    public static String getFeeUnit(String unit) {
        if ("day".equals(unit)){
            return "元/天";
        }

        return "元/月";
    }

    /**
     *  是否删除 1 是 0 否 默认0
     */
    public static enum consultStatus {
        DENGJIZHONG(1, "登记中"),WEIJIAODINGJIN(2, "未交订金"),YIJIAODINGJIN(3, "已交订金"),YITUIFEI(4, "已退费");
        private final Integer value;
        private final String name;
        private consultStatus(Integer value, String name) {this.value = value; this.name = name;}
        public Integer getValue() {return value;}
        public String getName() {return name;}
    }

    /**
     *  物品状态 1 未领取 2 已领取
     */
    public static enum goodsStatus {
        WEILINGAQU(1, "未领取"),YILINGQU(2, "已领取");
        private final Integer value;
        private final String name;
        private goodsStatus(Integer value, String name) {this.value = value; this.name = name;}
        public Integer getValue() {return value;}
        public String getName() {return name;}
    }

    /**
     *  护理类型 1 护理类别 2 护理项目
     */
    public static enum nurseType {
        HULILEIBIE(1, "护理类别"),HULIXIANGMU(2, "护理项目");
        private final Integer value;
        private final String name;
        private nurseType(Integer value, String name) {this.value = value; this.name = name;}
        public Integer getValue() {return value;}
        public String getName() {return name;}
    }


    /**
     *  机构分类
     */
    public static enum mapOrgCatagory {
        GUONEI(1, "国内养老"),LVJU(2, "旅居养老"),GUOWAI(3, "国外");
        private final Integer value;
        private final String name;
        private mapOrgCatagory(Integer value, String name) {this.value = value; this.name = name;}
        public Integer getValue() {return value;}
        public String getStringValue() {return String.valueOf(value);}
        public String getName() {return name;}
    }

    /**
     *  收费标准
     */
    public static enum feeStandrad {
        YIQIANYIXIA(1, "1000元/月以下"),YIQIANDAOLIANGQIAN(2, "1000-2000元/月")
        ,LIANGQDAOWUQ(3, "2000 - 5000元/月"),WUQIANYISHANG(4, "5000元/月以上");
        private final Integer value;
        private final String name;
        private feeStandrad(Integer value, String name) {this.value = value; this.name = name;}
        public Integer getValue() {return value;}
        public String getName() {return name;}
    }

    /**
     *  广告状态
     */
    public static enum advertiseStatus {
        SHANGJIA(1, "上架"),XIAJIA(2, "下架");
        private final Integer value;
        private final String name;
        private advertiseStatus(Integer value, String name) {this.value = value; this.name = name;}
        public Integer getValue() {return value;}
        public String getName() {return name;}
    }

    /**
     *  品牌状态
     */
    public static enum brandStatus {
        SHANGJIA(1, "上架"),XIAJIA(2, "下架");
        private final Integer value;
        private final String name;
        private brandStatus(Integer value, String name) {this.value = value; this.name = name;}
        public Integer getValue() {return value;}
        public String getName() {return name;}
    }

    /**
     *  机构审核状态
     */
    public static enum orgApprovalStatus {
        WEISHENHE(0, "未审核"),DAISHENHE(1, "待审核"),
        BUTONGGUO(2, "不通过"),TONGGUO(3, "通过");
        private final Integer value;
        private final String name;
        private orgApprovalStatus(Integer value, String name) {this.value = value; this.name = name;}
        public Integer getValue() {return value;}
        public String getName() {return name;}
    }

    /**
     *  机构状态
     */
    public static enum orgStatus {
        ZHENGSHI(0, "正式"),SHIYONG(1, "试用");
        private final Integer value;
        private final String name;
        private orgStatus(Integer value, String name) {this.value = value; this.name = name;}
        public Integer getValue() {return value;}
        public String getName() {return name;}
    }

    public static final int YIQIANYIXIA = 1;
    public static final int YIQIANDAOLIANGQIAN = 2;
    public static final int LIANGQDAOWUQ = 3;
    public static final int WUQIANYISHANG = 4;

}
