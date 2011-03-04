package cz.wt.convertor.main;

import cz.wt.convertor.main.gui.MainFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main class
 *
 */
public class Main {

  private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    try {
      // look and feel stejnz jako system
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

      // logovani nezchycenych vyjimek
      Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

        @Override
        public void uncaughtException(Thread thread, Throwable throwable) {
          LOGGER.error(thread.getName(), throwable);
        }
      });

      // spusteni hlavniho okna
      java.awt.EventQueue.invokeLater(new Runnable() {

        @Override
        public void run() {

          MainFrame mainFrame = new MainFrame();
          mainFrame.setVisible(true);
        }
      });

    } catch (ClassNotFoundException ex) {
      LOGGER.error("UIManager", ex);
    } catch (InstantiationException ex) {
      LOGGER.error("UIManager", ex);
    } catch (IllegalAccessException ex) {
      LOGGER.error("UIManager", ex);
    } catch (UnsupportedLookAndFeelException ex) {
      LOGGER.error("UIManager", ex);
    } catch (RuntimeException ex) {
      LOGGER.error("UIManager", ex);
      System.exit(1);
    }
  }
}
