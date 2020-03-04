package com.llw.hospital.common.util;

/**
 * Created by Administrator on 2018/3/26.
 */
import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.util.*;


/**
 *
 * @author eyes1842
 *
 * @param <K> Key type
 * @param <V> Value type
 */
public class MultiKeyMap<K,V> implements Serializable{

    private Map<K,MultiKeyMap.Entry<K, V>> map = new HashMap<K,MultiKeyMap.Entry<K, V>>();
    private Map<K,Set<K>> childMap = new HashMap<>();

    public boolean hasChildren(K pkey){
        return null != childMap.get(pkey) && childMap.get(pkey).size() > 0;
    }

    public V put(K pkey, K fkey, V value) {
        MultiKeyMap.Entry<K,V> entry = new MultiKeyMap.Entry<K,V>(fkey, value);
        map.put(pkey, entry);
        Set<K> cset = childMap.get(fkey);
        if(null == cset){
            cset = new HashSet<>();
            childMap.put(fkey,cset);
        }
        cset.add(pkey);
        return value;
    }

    public V get(K pkey) {
        MultiKeyMap.Entry<K,V> entry = map.get(pkey);
        if(entry!=null&&!entry.isEmpty()){

            return entry.getValue();
        }
        else {
            return null;
        }
    }

    public V remove(K pkey){
        Entry<K,V> e = map.remove(pkey);
        childMap.remove(pkey);
        return (e == null ? null : e.value);
    }

    public void clear(){
        map.clear();
        childMap.clear();
    }

    public Collection<V> values(){
        Collection<V> result = null;
        if(!map.isEmpty()){
            result = new ArrayList<V>(map.size());
            for(MultiKeyMap.Entry<K,V> entry : map.values()){
                result.add(entry.getValue());
            }
        }
        return result;
    }

    public Collection<V> getByFKey(K fkey) {
        Collection<V> result = new ArrayList<>();
        Set<K> cset = childMap.get(fkey);
        if(!CollectionUtils.isEmpty(cset)){
            for(K key:cset){
                result.add(map.get(key).getValue());
            }
        }
        return result;
    }

    //递归查找key
    public Collection<V> getRecursionByFKey(K fkey) {
        Collection<V> result = new ArrayList<>();
        getByFKey2(fkey,result);
        return result;
    }

    private void getByFKey2(K fkey,  Collection<V> result) {
        Set<K> cset = childMap.get(fkey);
        if(!CollectionUtils.isEmpty(cset)){
            for(K key:cset){
                getByFKey2(key, result);
                result.add(map.get(key).getValue());
            }
        }
    }

    static class Entry<K,V> implements Serializable {
        final K key;
        V value;

        Entry(K k, V v) {
            value = v;
            key = k;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public boolean isEmpty(){
            return (this==null||getKey()==null);
        }

        public final boolean equals(MultiKeyMap.Entry<K,V> o) {
            if (!(o instanceof MultiKeyMap.Entry)){
                return false;
            }
            Object k1 = getKey();
            Object k2 = o.getKey();
            if (k1 == k2 || (k1 != null && k1.equals(k2))) {
                Object v1 = getValue();
                Object v2 = o.getValue();
                if (v1 == v2 || (v1 != null && v1.equals(v2))) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public final int hashCode() {
            return (key==null   ? 0 : key.hashCode()) ^
                    (value==null ? 0 : value.hashCode());
        }

        @Override
        public final String toString() {
            return getKey() + "=" + getValue();
        }
    }

/*
    *//**
     * Test
     * @param args
     *//*
    public static void main(String[] args) {
        MultiKeyMap<String,String> map = new MultiKeyMap<String,String>();

        map.put("000000000000", "0", "中国");
        map.put("430000000000", "000000000000", "湖南");
        map.put("440000000000", "000000000000", "湖北");
        map.put("430100000000", "430000000000", "长沙市");
        map.put("440200000000", "440000000000", "武汉");
        map.put("430121000000", "430100000000", "长沙县");

        List<String> ss = (List<String>) map.getByFKey("000000000000");

        ss = (List<String>) map.getByFKey("430000000000");
        for(String s : ss)
            System.out.println(s);
        System.out.println("xxx");
        ss = (List<String>) map.getByFKey("440000000000");
        for(String s : ss)
            System.out.println(s);
        System.out.println("xxx");
        ss = (List<String>) map.getByFKey("430100000000");
        for(String s : ss)
            System.out.println(s);
    }*/
}

