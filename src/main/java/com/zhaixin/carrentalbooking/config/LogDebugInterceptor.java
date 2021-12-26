package com.zhaixin.carrentalbooking.config;

import lombok.extern.slf4j.Slf4j;
import org.beetl.sql.ext.DebugInterceptor;

@Slf4j
public class LogDebugInterceptor extends DebugInterceptor {

    @Override
    protected void println(String str) {
        super.println(str);
        log.debug(str);
    }
}
