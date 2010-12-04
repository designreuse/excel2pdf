/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.wt.convertor.exceltopdf.utils;

import java.awt.Component;
import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author forrest
 */
public class FileUtils {

    public static File chooseFile(Component controller, final List<String> listOfExtensionOpen, final String extensionsDescrOpen) {

        JFileChooser fileChooser = new JFileChooser();

        //nezobrazi moznost vybrat filtr all files
        fileChooser.setAcceptAllFileFilterUsed(false);

        // texty
        fileChooser.setDialogTitle("Pridani souboru");

        // vybirej pouze soubory a ne adresare
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // filtry
        fileChooser.addChoosableFileFilter(new FileFilter() {

            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                } else {
                    String path = file.getAbsolutePath().toLowerCase();
                    for (String extension : listOfExtensionOpen) {
                        if (path.endsWith(extension)) {
                            return true;
                        }
                    }
                }
                return false;
            }

            @Override
            public String getDescription() {
                return extensionsDescrOpen;
            }
        });

        // zobraz dialog
        int result = fileChooser.showDialog(controller, "pridej");

        // vybran soubour
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }

        return null;
    }

    public static File saveFile(Component controller, String fileName, final String extensionSave, final String extensionsDescrSave) {

        JFileChooser fileChooser = new JFileChooser();

        //nezobrazi moznost vybrat filtr all files
        fileChooser.setAcceptAllFileFilterUsed(false);

        if (fileName != null) {
            // prednastaveni jmena souboru
            if (fileName.endsWith(extensionSave)) {
                fileName = fileName.substring(0, fileName.length() - 4);
            }
            fileChooser.setSelectedFile(new File(fileName));
        }

        // texty
        fileChooser.setDialogTitle("Ulozeni souboru");

        // vybirej pouze soubory a ne adresare
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // filtry
        fileChooser.addChoosableFileFilter(new FileFilter() {

            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                } else {
                    String path = file.getAbsolutePath().toLowerCase();
                    if (path.endsWith(extensionSave)) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public String getDescription() {
                return extensionsDescrSave;
            }
        });

        // zobraz dialog
        int result = fileChooser.showDialog(controller, "uloz");

        // vybran soubor
        if (result == JFileChooser.APPROVE_OPTION) {
            File saveFile = fileChooser.getSelectedFile();
            File saveFileExtension = saveFile;

            // pokud uzivatel napise priponu jiz ji nepridavam
            if (!saveFile.getPath().endsWith(extensionSave)) {
                saveFileExtension = new File(saveFile.getPath().concat(extensionSave));
            }

            // overwrite ?
            if (saveFileExtension.exists()) {

                final JOptionPane optionPane = new JOptionPane("Soubor existuje ma se prepsat? ",
                        JOptionPane.QUESTION_MESSAGE,
                        JOptionPane.YES_NO_OPTION);

                if (optionPane.getOptionType() == JOptionPane.NO_OPTION) {
                    return FileUtils.saveFile(controller, fileName, extensionSave, extensionsDescrSave);
                }
            }

            return saveFileExtension;
        }

        return null;
    }
}
