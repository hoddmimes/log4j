package com.hoddmimes.log;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.*;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

public class Auxlog4j
{
    public static void main(String[] args) {
        Auxlog4j t = new Auxlog4j();
        t.setup();

        Logger tLogger = LogManager.getLogger(Auxlog4j.class);

        tLogger.info("test info message");
        tLogger.debug("test debug message");
        tLogger.warn("test warn message");
        tLogger.error("test error message");
        tLogger.fatal("test fatal message");

    }


    private void setup() {
        ConfigurationBuilder<BuiltConfiguration> tBuilder = ConfigurationBuilderFactory.newConfigurationBuilder();

        tBuilder.setStatusLevel(Level.INFO);
        tBuilder.setConfigurationName("PgmTest");
        // Add File appender
        AppenderComponentBuilder tLogfile = tBuilder.newAppender("logfile", "File");
        tLogfile.addAttribute("fileName", "logtst.log");


        // Add stdout appender
        AppenderComponentBuilder tConsole = tBuilder.newAppender("stdout", "Console");

        // Add layout for Console
        LayoutComponentBuilder tConsoleLayout = tBuilder.newLayout("PatternLayout");
        tConsoleLayout.addAttribute("pattern", "%d{HH:mm:ss.SSS} %C{1} %-5level  - %msg%n");
        tConsole.add( tConsoleLayout );
        tBuilder.add( tConsole );

        // Add layout for Logfile
        LayoutComponentBuilder tFileLayout = tBuilder.newLayout("PatternLayout");
        tFileLayout.addAttribute("pattern", "%d{HH:mm:ss.SSS} %C{1} %-5level  - %msg%n");
        tLogfile.add( tFileLayout );
        tBuilder.add( tLogfile );

        RootLoggerComponentBuilder tRootLogger = tBuilder.newRootLogger(Level.TRACE);
        tRootLogger.add(tBuilder.newAppenderRef("stdout"));
        tRootLogger.add(tBuilder.newAppenderRef("logfile"));
        tBuilder.add( tRootLogger);

        Configurator.initialize(tBuilder.build());

    }

}
