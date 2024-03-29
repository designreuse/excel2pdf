/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FileChooser.java
 *
 * Created on 3.12.2010, 21:12:31
 */
package cz.wt.convertor.main.gui.bean;

import cz.wt.convertor.main.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JPanel;
import org.jdesktop.application.Action;

/**
 *
 * @author forrest
 */
public class FileChooser extends JPanel {

  private List<String> listOfExtensionOpen = new ArrayList<String>();

  private String extensionsDescrOpen = "";

  private String extensionSave = "";

  private String extensionsDescrSave = "";

  private File file;

  /** Creates new form FileChooser */
  public FileChooser() {
    initComponents();
  }

  public File getFile() {
    return file;
  }

  public void addAllowExtensionsOpen(String... extensions) {
    listOfExtensionOpen.addAll(Arrays.asList(extensions));
  }

  public void setExtensionsDescrOpen(String extensionsDescr) {
    this.extensionsDescrOpen = extensionsDescr;
  }

  public void addAllowExtensionsSave(String extensions) {
    extensionSave = extensions;
  }

  public void setExtensionsDescrSave(String extensionsDescr) {
    this.extensionsDescrSave = extensionsDescr;
  }

  @Override
  public void enable(boolean b) {
    jButtonOpen.setEnabled(b);
    jTextField1.setEditable(b);
  }

  public void setVisibleButtonOpen(boolean visible) {
    jButtonOpen.setVisible(visible);
  }

  public void setVisibleButtonSave(boolean visible) {
    jButtonSave.setVisible(visible);
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    java.awt.GridBagConstraints gridBagConstraints;

    container = new javax.swing.JPanel();
    jButtonOpen = new javax.swing.JButton();
    jTextField1 = new javax.swing.JTextField();
    jButtonSave = new javax.swing.JButton();

    setName("Form"); // NOI18N
    setLayout(new java.awt.GridBagLayout());

    container.setName("container"); // NOI18N
    container.setLayout(new java.awt.GridBagLayout());

    javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(FileChooser.class, this);
    jButtonOpen.setAction(actionMap.get("openFile")); // NOI18N
    jButtonOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Add_16x16.png"))); // NOI18N
    java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("cz/wt/convertor/main/gui/bean/Bundle"); // NOI18N
    jButtonOpen.setText(bundle.getString("add")); // NOI18N
    jButtonOpen.setDoubleBuffered(true);
    jButtonOpen.setMinimumSize(new java.awt.Dimension(22, 22));
    jButtonOpen.setName("jButtonOpen"); // NOI18N
    jButtonOpen.setPreferredSize(new java.awt.Dimension(25, 25));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    container.add(jButtonOpen, gridBagConstraints);

    jTextField1.setMinimumSize(new java.awt.Dimension(23, 23));
    jTextField1.setName("jTextField1"); // NOI18N
    jTextField1.setPreferredSize(new java.awt.Dimension(23, 23));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
    container.add(jTextField1, gridBagConstraints);

    jButtonSave.setAction(actionMap.get("fileSave")); // NOI18N
    jButtonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Save_16x16.png"))); // NOI18N
    jButtonSave.setText(bundle.getString("save")); // NOI18N
    jButtonSave.setMaximumSize(new java.awt.Dimension(25, 25));
    jButtonSave.setMinimumSize(new java.awt.Dimension(22, 22));
    jButtonSave.setName("jButtonSave"); // NOI18N
    jButtonSave.setPreferredSize(new java.awt.Dimension(25, 25));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    container.add(jButtonSave, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    add(container, gridBagConstraints);
    org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(FileChooser.class);
    resourceMap.injectComponents(this);
  }// </editor-fold>//GEN-END:initComponents

  @Action
  public void openFile() {
    file = FileUtils.chooseFile(container, listOfExtensionOpen, extensionsDescrOpen);
    if (file != null) {
      jTextField1.setText(file.getPath());
    }
  }

  @Action
  public void fileSave() {
    file = FileUtils.saveFile(container, jTextField1.getText(), extensionSave, extensionsDescrSave);
  }
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel container;
  private javax.swing.JButton jButtonOpen;
  private javax.swing.JButton jButtonSave;
  private javax.swing.JTextField jTextField1;
  // End of variables declaration//GEN-END:variables
}
