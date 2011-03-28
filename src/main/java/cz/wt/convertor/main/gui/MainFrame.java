/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainFrame.java
 *
 * Created on 28.2.2011, 22:41:39
 */
package cz.wt.convertor.main.gui;

import javax.swing.ImageIcon;
import org.slf4j.Logger;

/**
 *
 * @author forrest
 */
public class MainFrame extends javax.swing.JFrame {

  private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(MainFrame.class);

  /** Creates new form MainFrame */
  public MainFrame() {
    initComponents();
    setSize(650, 116);
    setResizable(false);
    setLocationRelativeTo(null);
    ImageIcon imageIcon = new javax.swing.ImageIcon(getClass().getResource("/icons/excel.png"));
    setIconImage(imageIcon.getImage());
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        obsahFrame = new cz.wt.convertor.main.gui.ObsahFrame();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        helpMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("cz/wt/convertor/main/gui/Bundle"); // NOI18N
        setTitle(bundle.getString("MainFrame.title")); // NOI18N
        setMinimumSize(new java.awt.Dimension(300, 30));
        getContentPane().setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(obsahFrame, gridBagConstraints);

        fileMenu.setText(bundle.getString("MainFrame.fileMenu.text")); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(MainFrame.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(bundle.getString("MainFrame.helpMenu.text")); // NOI18N

        helpMenuItem.setAction(actionMap.get("showHelpBox")); // NOI18N
        helpMenuItem.setText(bundle.getString("MainFrame.helpMenuItem.text")); // NOI18N
        helpMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                napoveda(evt);
            }
        });
        helpMenu.add(helpMenuItem);

        aboutMenuItem.setText(bundle.getString("MainFrame.aboutMenuItem.text")); // NOI18N
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                about(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void about(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_about
      AboutFrame aboutFrame = new AboutFrame();
      aboutFrame.setLocationRelativeTo(this);
      aboutFrame.setVisible(true);
    }//GEN-LAST:event_about

    private void napoveda(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_napoveda
      HelpFrame helpFrame = new HelpFrame();
      helpFrame.setLocationRelativeTo(null);
      helpFrame.setVisible(true);
    }//GEN-LAST:event_napoveda
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem helpMenuItem;
    private javax.swing.JMenuBar menuBar;
    private cz.wt.convertor.main.gui.ObsahFrame obsahFrame;
    // End of variables declaration//GEN-END:variables
}
