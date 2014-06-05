package com.example;

import com.avaje.lib.ConsoleLogger;

/**
 * A console logger that suppresses warning messages.  This is to prevent big
 * NoClassDefFoundError stack traces from  going to the console when Ebean does
 * it's reflection magic (e.g. disabled unitils modules with missing
 * dependencies).
 */
public class WarnlessConsoleLogger extends ConsoleLogger
{
  @Override
  public void warning(String msg, Throwable ex)
  {
  }
}
