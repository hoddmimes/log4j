package com.hoddmimes.log;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.*;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

public class LogTst
{
    public static void main(String[] args) {
        LogTst t = new LogTst();
        t.setup();

        Logger tLogger = LogManager.getLogger(LogTst.class);

        tLogger.info("test info message");
        tLogger.debug("test debug message");
        tLogger.warn("test warn message");
        tLogger.error("test error message");
        tLogger.fatal("test fatal message");

    }


    private void setup() {
        ConfigurationBuilder<BuiltConfiguration> tBuilder = ConfigurationBuilderFactory.newConfigurationBuilder();

        // Add File appender
        AppenderComponentBuilder tLogfile = tBuilder.newAppender("logfile", "File");
        tLogfile.addAttribute("fileName", "logtst.log");

        // Add stdout appender
        AppenderComponentBuilder tConsole = tBuilder.newAppender("stdout", "Console");

        // Add layout for Console
        LayoutComponentBuilder tStdoutLayout = tBuilder.newLayout("PatternLayout");
        tStdoutLayout.addAttribute("pattern", "%d{HH:mm:ss.SSS} %C{1} %-5level  - %msg%n");
        tConsole.add( tStdoutLayout );

        // Add layout for Logfile
        LayoutComponentBuilder tFileLayout = tBuilder.newLayout("PatternLayout");
        tStdoutLayout.addAttribute("pattern", "%d{HH:mm:ss.SSS} %C{1} %-5level  - %msg%n");
        tConsole.add( tStdoutLayout );

        RootLoggerComponentBuilder tRootLogger = tBuilder.newRootLogger(Level.TRACE);
        tRootLogger.add(tBuilder.newAppenderRef("stdout"));
        tRootLogger.add(tBuilder.newAppenderRef("logfile"));

        Configurator.initialize(tBuilder.build());

    }

}
