package com.llw.hospital.util;

import org.springframework.util.CollectionUtils;

import java.util.*;

public class OrgUtil {

/*    public static CachedSysOrganizationDto getOrgFromCache(Long orgId){
        byte[] bytes = ("orgCache:" + orgId.toString()).getBytes();
        byte[] cached = LlwRedisUtil.get(bytes);
        if(null != cached){
            return (CachedSysOrganizationDto) SerializationUtils.deserialize(cached);
        }else{
            return null;
        }
    }*/

   /* *//**
     * 获取当前机构支持的业务类型
     * @param orgId
     * @return
     *//*
    public static List<String> getBusiTypes(Long orgId) {
        CachedSysOrganizationDto dto = getOrgFromCache(orgId);
        if(null != dto){
            return dto.getBusiTypes();
        }
        return Collections.emptyList();
    }*/

/*    *//**
     * 获取当前机构能够查看的系统类型
     * @param orgId
     * @return
     *//*
    public static String getSysCodes(Long orgId) {
        List<String> sysCodes = new ArrayList<>();
        CachedSysOrganizationDto dto = getOrgFromCache(orgId);
        if(null != dto && !CollectionUtils.isEmpty(dto.getSysCodes())){
            dto.getSysCodes().forEach(item->{
                sysCodes.add(String.valueOf(item));
            });
        }

        return String.join(",",sysCodes);
    }*/

/*    public static void setOrgToCache(CachedSysOrganizationDto sysOrganizationDto) {
        byte[] bytes = ("orgCache:" + sysOrganizationDto.getOrgId().toString()).getBytes();
        LlwRedisUtil.set(bytes, SerializationUtils.serialize(sysOrganizationDto));
    }*/

    public static boolean isChildId(Long topOrgId, Long orgId) {
        return orgId>topOrgId && orgId < OrgUtil.getMaxLeafTheoreticalValue(topOrgId);
    }

    /**
     * 根据父节点和所有子机构id获取id
     * @param pid
     * @param childIds
     * @return
     */
    public static Long generatorOrgId(Long pid,List<Long> childIds){
            //获取当前父节点等级
            int fatherLevel = getOrgLevel(pid);
            Long maxChildrenCount = maxNumberPerLevel.get(fatherLevel+1);
            Long shiftBit = shiftPerLevel.get(fatherLevel+1);

            if( CollectionUtils.isEmpty(childIds)){
                return pid |(1L<<shiftBit);
            }else{
                //寻找可用的下标
                for(long i = 1; i <= maxChildrenCount; i++){
                    boolean currentIndexUsed = false;
                    for(Long brotherId:childIds){
                        Long brotherIndex = ((brotherId&(maxChildrenCount<<shiftBit))>>shiftBit);
                        if(brotherIndex == i){
                            currentIndexUsed = true;
                        }
                    }
                    //如果当前下标没有被占用，则可以使用
                    if(!currentIndexUsed){
                        return pid | (i<<shiftBit);
                    }
                }
                throw new RuntimeException("子节点数不能超过限制"+maxChildrenCount+"个");
            }
    }

    public static Map<Integer,Long> maxNumberPerLevel = new HashMap<>(6);
    public static Map<Integer,Long> shiftPerLevel = new HashMap<>(6);

    //每个等级的有效位数
    public static Map<Integer,Integer> validBitsPerLevel = new HashMap<>(7);

    static {
        shiftPerLevel.put(1,48L);
        shiftPerLevel.put(2,38L);
        shiftPerLevel.put(3,28L);
        shiftPerLevel.put(4,18L);
        shiftPerLevel.put(5,8L);
        shiftPerLevel.put(6,0L);

        maxNumberPerLevel.put(1,15L);//一级类型个数 医院 医保中心  工伤中心等
        maxNumberPerLevel.put(2,1023L);//医院个数
        maxNumberPerLevel.put(3,1023L);//科室个数
        maxNumberPerLevel.put(4,1023L);
        maxNumberPerLevel.put(5,1023L);
        maxNumberPerLevel.put(6,255L);

        validBitsPerLevel.put(0,12);
        validBitsPerLevel.put(1,16);
        validBitsPerLevel.put(2,26);
        validBitsPerLevel.put(3,36);
        validBitsPerLevel.put(4,46);
        validBitsPerLevel.put(5,56);
        validBitsPerLevel.put(6,64);
    }

    /**
     * 根据机构编码获取当前机构的等级
     * @param orgId
     * @return
     */
    public static int getOrgLevel(Long orgId) {
        int maxLevel = maxNumberPerLevel.size();
        for(int i = 1; i <= maxLevel; i++){
            long mask = maxNumberPerLevel.get(i);
            long shift = shiftPerLevel.get(i);
            if((orgId & mask<<shift) == 0){
                return i-1;
            }
        }
        return maxLevel;
    }

    /**
     * 获取理论上的最大的叶子节点的值，主要用作数据库的数据权限筛选
     */
    public static Long getMaxLeafTheoreticalValue(Long orgId){
//        print("当前orgId",orgId);
        //获取当前机构的等级
        int level = getOrgLevel(orgId);
        //当前机构等级之前的数据都是有效的 末尾的都补上1即为所有的子孙节点的理论最大值
        Long stayMask = (Long.MAX_VALUE >> (validBitsPerLevel.get(level)-1));
//        print("掩码",stayMask);
        Long result =  (orgId | stayMask);
//        print("最大值",result);
        return result;
    }

    private static void print(String key,Long topId) {
        String binaryString = Long.toBinaryString(topId);
        String mask = "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        String result = mask.substring(0,(64-binaryString.length())) + binaryString;
        System.out.println(key+":"+topId+"\r\n对应的二进制形式是"+ result);
    }

}
