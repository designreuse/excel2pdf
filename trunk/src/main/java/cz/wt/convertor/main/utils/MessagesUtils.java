package cz.wt.convertor.main.utils;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * 
 * @author diblik
 * @version $Revision$
 */
public class MessagesUtils {

    public static void showInfo(Component component, String infoMsg) {
        JOptionPane.showMessageDialog(component,
                infoMsg,
                "Info dialog",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showWarning(Component component, String warnMsg) {
        JOptionPane.showMessageDialog(component,
                warnMsg,
                "Warning dialog",
                JOptionPane.WARNING_MESSAGE);
    }

    public static void showError(Component component, String errorMsg) {
        JOptionPane.showMessageDialog(component,
                errorMsg,
                "Error dialog",
                JOptionPane.ERROR_MESSAGE);

    }

    public static int showOptionDialog(Component component, String errorMsg) {
        Object[] options = {"Ano", "Ne"};
        int n = JOptionPane.showOptionDialog(component,
                errorMsg,
                "Option dialog",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);
        return n;
    }
}
