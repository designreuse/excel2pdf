package cz.wt.convertor.main;

import cz.wt.convertor.main.gui.MainFrame;
import java.util.logging.Level;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Main class
 *
 */
public class Main {

  private static final LoggerHandler LOG = LoggerFactory.getLogger(Main.class);

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      java.awt.EventQueue.invokeLater(new Runnable() {

        @Override
        public void run() {
          try {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
          } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            System.exit(1);
          }
        }
      });

    } catch (ClassNotFoundException ex) {
      LOG.log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      LOG.log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      LOG.log(Level.SEVERE, null, ex);
    } catch (UnsupportedLookAndFeelException ex) {
      LOG.log(Level.SEVERE, null, ex);
    } catch (RuntimeException ex) {
      LOG.log(Level.SEVERE, null, ex);
      System.exit(1);
    }
  }
}
