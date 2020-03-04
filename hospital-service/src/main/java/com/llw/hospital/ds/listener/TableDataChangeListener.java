package com.llw.hospital.ds.listener;

import com.jcl.common.spring.context.AppContextHolder;
import com.jcl.orm.monitor.listener.event.TableDataChangeEvent;
import com.llw.cache.CacheClient;
import com.llw.cache.TableAssociatedCacheService;
import com.llw.hospital.common.base.constants.BaseConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Map;

/**
 * 监听表数据变化并自动刷新缓存
 */
@Component
public class TableDataChangeListener {

    private Logger logger = LoggerFactory.getLogger(TableDataChangeListener.class);

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT,fallbackExecution = true)
    public void refreshCache(TableDataChangeEvent event){
        Map<String, TableAssociatedCacheService> allCacheServices = AppContextHolder.getBeansOfType(TableAssociatedCacheService.class);
        if(!allCacheServices.isEmpty()){
            for(TableAssociatedCacheService cacheService:allCacheServices.values()){
                if(cacheService.associatedWith(event.getTableName())){
                    logger.info("通知增量刷新缓存{}",cacheService.getCacheName());
                    CacheClient.refresh(cacheService.getCacheName()+"?"+ BaseConstants.CACHE_REFRESH_TYPE+"="+BaseConstants.CACHE_REFRESH_TYPE_DELTA);
                }
            }
        }
    }
}
