package com.llw.hospital.bs.common.listener;

import java.util.List;

/**
 * Created by Administrator on 2018/3/14.
 */
public interface CacheListener {

    List<String> getAllListenCacheNames();

    void cacheChanged(String cacheName);
}
