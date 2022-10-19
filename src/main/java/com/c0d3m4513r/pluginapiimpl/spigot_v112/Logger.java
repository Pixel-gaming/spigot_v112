package com.c0d3m4513r.pluginapiimpl.spigot_v112;

import com.c0d3m4513r.plugindef.Plugin;
import lombok.*;
import org.slf4j.Marker;
import org.slf4j.event.Level;

import java.util.Arrays;

@RequiredArgsConstructor
@EqualsAndHashCode
public class Logger implements org.slf4j.Logger {

    @Setter
    @Getter
    @NonNull
    Level level = Level.INFO;
    @NonNull
    @Getter
    java.util.logging.Logger logger;

    @Override
    public String getName() {
        return Plugin.name;
    }

    @Override
    public boolean isTraceEnabled() {
        return level.toInt()<=Level.TRACE.toInt();
    }

    @Override
    public void trace(String msg) {
        logger.log(java.util.logging.Level.FINER,msg);
    }

    @Override
    public void trace(String format, Object arg) {
        logger.log(java.util.logging.Level.FINER,format,arg);
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        logger.log(java.util.logging.Level.FINER,format,new Object[]{arg1,arg2});
    }

    @Override
    public void trace(String format, Object... arguments) {
        logger.log(java.util.logging.Level.FINER,format, Arrays.stream(arguments).toArray());
    }

    @Override
    public void trace(String msg, Throwable t) {
        logger.log(java.util.logging.Level.FINER,msg,t);
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return isTraceEnabled();
    }

    @Override
    public void trace(Marker marker, String msg) {
        trace(msg);
    }

    @Override
    public void trace(Marker marker, String format, Object arg) {
        trace(format,arg);
    }

    @Override
    public void trace(Marker marker, String format, Object arg1, Object arg2) {
        trace(format,arg1,arg2);
    }

    @Override
    public void trace(Marker marker, String format, Object... argArray) {
        trace(format,argArray);
    }

    @Override
    public void trace(Marker marker, String msg, Throwable t) {
        trace(msg,t);
    }

    @Override
    public boolean isDebugEnabled() {
        return level.toInt()<=Level.DEBUG.toInt();
    }

    @Override
    public void debug(String msg) {
        logger.log(java.util.logging.Level.FINE,msg);
    }

    @Override
    public void debug(String format, Object arg) {
        logger.log(java.util.logging.Level.FINE,format,arg);

    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        logger.log(java.util.logging.Level.FINE,format,new Object[]{arg1,arg2});

    }

    @Override
    public void debug(String format, Object... arguments) {
        logger.log(java.util.logging.Level.FINE,format, Arrays.stream(arguments).toArray());
    }

    @Override
    public void debug(String msg, Throwable t) {
        logger.log(java.util.logging.Level.FINE,msg,t);
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return isDebugEnabled();
    }

    @Override
    public void debug(Marker marker, String msg) {
        debug(msg);
    }

    @Override
    public void debug(Marker marker, String format, Object arg) {
        debug(format,arg);
    }

    @Override
    public void debug(Marker marker, String format, Object arg1, Object arg2) {
        debug(format,arg1,arg2);
    }

    @Override
    public void debug(Marker marker, String format, Object... arguments) {
        debug(format,arguments);
    }

    @Override
    public void debug(Marker marker, String msg, Throwable t) {
        debug(msg,t);
    }

    @Override
    public boolean isInfoEnabled() {
        return level.toInt()<=Level.INFO.toInt();
    }

    @Override
    public void info(String msg) {
        logger.log(java.util.logging.Level.INFO,msg);
    }

    @Override
    public void info(String format, Object arg) {
        logger.log(java.util.logging.Level.INFO,format,arg);
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        logger.log(java.util.logging.Level.INFO,format,new Object[]{arg1,arg2});
    }

    @Override
    public void info(String format, Object... arguments) {
        logger.log(java.util.logging.Level.INFO,format, Arrays.stream(arguments).toArray());
    }

    @Override
    public void info(String msg, Throwable t) {
        logger.log(java.util.logging.Level.INFO,msg,t);
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return isInfoEnabled();
    }

    @Override
    public void info(Marker marker, String msg) {
        info(msg);
    }

    @Override
    public void info(Marker marker, String format, Object arg) {
        info(format,arg);
    }

    @Override
    public void info(Marker marker, String format, Object arg1, Object arg2) {
        info(format,arg1,arg2);
    }

    @Override
    public void info(Marker marker, String format, Object... arguments) {
        info(format,arguments);
    }

    @Override
    public void info(Marker marker, String msg, Throwable t) {
        info(msg,t);
    }

    @Override
    public boolean isWarnEnabled() {
        return level.toInt()<=Level.WARN.toInt();
    }

    @Override
    public void warn(String msg) {
        logger.log(java.util.logging.Level.WARNING,msg);
    }

    @Override
    public void warn(String format, Object arg) {
        logger.log(java.util.logging.Level.WARNING,format,arg);
    }

    @Override
    public void warn(String format, Object... arguments) {
        logger.log(java.util.logging.Level.WARNING,format, Arrays.stream(arguments).toArray());
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        logger.log(java.util.logging.Level.WARNING,format,new Object[]{arg1,arg2});
    }

    @Override
    public void warn(String msg, Throwable t) {
        logger.log(java.util.logging.Level.WARNING,msg,t);
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return isWarnEnabled();
    }

    @Override
    public void warn(Marker marker, String msg) {
        warn(msg);
    }

    @Override
    public void warn(Marker marker, String format, Object arg) {
        warn(format, arg);
    }

    @Override
    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        warn(format, arg1, arg2);
    }

    @Override
    public void warn(Marker marker, String format, Object... arguments) {
        warn(format, arguments);
    }

    @Override
    public void warn(Marker marker, String msg, Throwable t) {
        warn(msg, t);
    }

    @Override
    public boolean isErrorEnabled() {
        return level.toInt()<=Level.ERROR.toInt();
    }

    @Override
    public void error(String msg) {
        logger.log(java.util.logging.Level.SEVERE,msg);
    }

    @Override
    public void error(String format, Object arg) {
        logger.log(java.util.logging.Level.SEVERE,format,arg);
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        logger.log(java.util.logging.Level.SEVERE,format,new Object[]{arg1,arg2});
    }

    @Override
    public void error(String format, Object... arguments) {
        logger.log(java.util.logging.Level.SEVERE,format, Arrays.stream(arguments).toArray());
    }

    @Override
    public void error(String msg, Throwable t) {
        logger.log(java.util.logging.Level.SEVERE,msg,t);
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return isErrorEnabled();
    }

    @Override
    public void error(Marker marker, String msg) {
        error(msg);
    }

    @Override
    public void error(Marker marker, String format, Object arg) {
        error(format, arg);
    }

    @Override
    public void error(Marker marker, String format, Object arg1, Object arg2) {
        error(format, arg1, arg2);
    }

    @Override
    public void error(Marker marker, String format, Object... arguments) {
        error(format, arguments);
    }

    @Override
    public void error(Marker marker, String msg, Throwable t) {
        error(msg, t);
    }
}
