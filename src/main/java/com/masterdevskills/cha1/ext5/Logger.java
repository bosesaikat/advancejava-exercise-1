package com.masterdevskills.cha1.ext5;

import java.util.function.Supplier;

//TODO: implement info, trace, debug, warn
public class Logger implements Log {

  private static final String DELIM = "{}";
  private volatile boolean enabled;
  private volatile boolean debugEnable;
  private volatile boolean traceEnable;
  private volatile boolean warnEnable;

  private Logger() {
  }

  public static Log getLogger() {
    return new Logger();
  }

  @Override
  public boolean isLoggable() {
    return enabled;
  }

  @Override
  public void enableLogging() {
    this.enabled = true;
  }

  @Override
  public boolean isDebugEnable() {
    return debugEnable;
  }

  @Override
  public void enableDebugging() {
    this.debugEnable = true;
  }

  @Override
  public boolean isTraceEnable() {
    return traceEnable;
  }

  @Override
  public void enableTracing() {
    this.traceEnable = true;
  }

  @Override
  public boolean isWarnEnable() {
    return warnEnable;
  }

  @Override
  public void enableWarning() {
    this.warnEnable = true;
  }

  @Override
  public void info(final String message, final Object... params) {
    if (isLoggable()) {
      System.out.println(formatMessage(message, params));
    }
  }

  private String formatMessage(final String message, final Object[] params) {
    if (message != null && params != null) {
      StringBuilder sbMessage = new StringBuilder(message);

      for (Object arg : params) {
        int index = sbMessage.indexOf(DELIM);
        if (index == -1) {
          break;
        }
        sbMessage.replace(index, index + DELIM.length(), arg == null ? "null" : arg.toString());
      }

      return sbMessage.toString();
    }
    return message;
  }

  @Override
  public void info(final String message, final Supplier<Object[]> params) {
    if (isLoggable()) {
      doLog(formatMessage(message, params.get()));
    }
  }

  @Override
  public void debug(String message, Supplier<Object[]> params) {
    if (isDebugEnable()) {
      doLog(formatMessage(message, params.get()));
    }
  }

  @Override
  public void trace(String message, Supplier<Object[]> params) {
    if (isTraceEnable()) {
      doLog(formatMessage(message, params.get()));
    }
  }

  @Override
  public void warn(String message, Supplier<Object[]> params) {
    if (isWarnEnable()) {
      doLog(formatMessage(message, params.get()));
    }
  }

  public Logger setEnabled(final boolean enabled) {
    this.enabled = enabled;
    return this;
  }
}


