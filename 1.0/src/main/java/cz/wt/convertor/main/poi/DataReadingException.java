// $Id$
// Klasifikace: CHRÁNÌNÉ
package cz.wt.convertor.main.poi;

/**
 * 
 * @author diblik
 * @version $Revision$
 */
public class DataReadingException extends Exception {

  private static final long serialVersionUID = 1L;

  public DataReadingException(Throwable cause) {
    super(cause);
  }

  public DataReadingException(String message, Throwable cause) {
    super(message, cause);
  }

  public DataReadingException(String message) {
    super(message);
  }
}
