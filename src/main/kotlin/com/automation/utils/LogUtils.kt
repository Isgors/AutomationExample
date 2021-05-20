package com.automation.utils

import org.apache.log4j.BasicConfigurator
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.slf4j.LoggerFactory

inline fun <reified T> T.logger(): org.slf4j.Logger {
    if (T::class.isCompanion) {
        return LoggerFactory.getLogger(T::class.java.enclosingClass)
    }
    BasicConfigurator.configure()
    Logger.getRootLogger().level = Level.INFO
    return LoggerFactory.getLogger(T::class.java)
}

