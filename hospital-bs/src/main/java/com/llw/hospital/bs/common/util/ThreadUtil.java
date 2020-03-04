package com.llw.hospital.bs.common.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadUtil {
    public static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);

    public static void execute(Runnable runnable){
        fixedThreadPool.execute(runnable);
    }
}
