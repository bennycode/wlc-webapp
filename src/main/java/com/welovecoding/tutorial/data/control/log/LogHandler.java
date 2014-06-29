package com.welovecoding.tutorial.data.control.log;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 *
 * @author Michael Koppen
 */
public class LogHandler extends Handler {

  private int messagesThreshold = 100;
  private LinkedBlockingDeque<String> logMessages = new LinkedBlockingDeque<>(messagesThreshold);

  @Override
  public void publish(LogRecord record) {
    // ### DO NOT LOG OR SYSO IN THIS METHOD!!! IT WILL CAUSE INFINITE LOOP! ###
    StringBuilder log = new StringBuilder();
    log.append(record.getLevel()).
            append(" : ").
            append(new Date(record.getMillis())).
            append(" : ").
            append(record.getMessage());

    if (logMessages.remainingCapacity() > 0) {
      logMessages.offerLast(log.toString());
    } else {
      logMessages.pollFirst();
      logMessages.offerLast(log.toString());
    }
    // ### DO NOT LOG OR SYSO IN THIS METHOD!!! IT WILL CAUSE INFINITE LOOP! ###
  }

  @Override
  public void flush() {

  }

  @Override
  public void close() throws SecurityException {

  }

  public int getMessagesThreshold() {
    return messagesThreshold;
  }

  public void setMessagesThreshold(int messagesThreshold) {
    this.messagesThreshold = messagesThreshold;
    List<String> oldMessages = new LinkedList<>();
    for (String string : logMessages) {
      oldMessages.add(string);
    }
    logMessages = new LinkedBlockingDeque<>(messagesThreshold);
    for (String string : oldMessages) {
      if (logMessages.remainingCapacity() > 0) {
        logMessages.offerLast(string);
      } else {
        logMessages.pollFirst();
        logMessages.offerLast(string);
      }
    }
  }

  public Iterator<String> getLogMessages() {
    return logMessages.iterator();
  }

  public Iterator<String> getDescendingLogMessages() {
    return logMessages.descendingIterator();
  }

}
