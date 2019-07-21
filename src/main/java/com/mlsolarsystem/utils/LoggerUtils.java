package com.mlsolarsystem.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

/**
 * Created by tom
 */
public class LoggerUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerUtils.class);
    private static final String ENDS_IN_MS = "Ends {} in {} ms";
    private static final String STARTING = "Starting {}";

    private LoggerUtils() {
        super();
    }

    public static <T> T benchmark(String action, Supplier<T> supplier){
        long init = System.currentTimeMillis();
        LOGGER.info(STARTING, action);
        T result = supplier.get();
        LOGGER.info(ENDS_IN_MS, action, System.currentTimeMillis() - init);
        return result;
    }
}
