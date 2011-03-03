// $Id$
// Klasifikace: CHRÁNÌNÉ
package cz.wt.convertor.main;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

/**
 * 
 * @author diblik
 * @version $Revision$
 */
public class LoggerHandler {

  private static java.util.logging.Logger logger;

  private String className = "";

  public LoggerHandler(String className) {
    this.className = className;
  }

  static {
    try {

      FileHandler fh = new FileHandler("excel2PdfLog.log", false);
      fh.setFormatter(new SimpleFormatter());
      logger = java.util.logging.Logger.getLogger("TestLog");
      logger.addHandler(fh);
    } catch (IOException e) {
    }
  }

  public void log(Level level, String msg) {
    logger.logp(level, className, null, msg);
  }

  public void log(Level level, String msg, Throwable throwable) {
    logger.logp(level, className, null, msg, throwable);
  }
}
