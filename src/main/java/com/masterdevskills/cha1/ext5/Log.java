package com.masterdevskills.cha1.ext5;

import java.util.function.Supplier;

public interface Log {
    boolean isLoggable();

    void enableLogging();

    boolean isDebugEnable();

    void enableDebugging();

    boolean isTraceEnable();

    void enableTracing();

    boolean isWarnEnable();

    void enableWarning();

    void info(String message, Object... params);

    void info(String message, Supplier<Object[]> params);

    void debug(String message, Supplier<Object[]> params);

    void trace(String message, Supplier<Object[]> params);

    void warn(String message, Supplier<Object[]> params);

    default void doLog(String message){
        System.out.println(message);
    }

}
