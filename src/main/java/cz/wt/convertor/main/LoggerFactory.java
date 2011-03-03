// $Id$
// Klasifikace: CHRÁNÌNÉ
package cz.wt.convertor.main;

/**
 * 
 * @author diblik
 * @version $Revision$
 */
public class LoggerFactory {

  public static LoggerHandler getLogger(Class clazz) {
    return new LoggerHandler(clazz.getName());
  }
}
