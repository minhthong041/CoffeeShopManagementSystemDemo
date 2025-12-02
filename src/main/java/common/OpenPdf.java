package common;

import java.io.File;
import javax.swing.JOptionPane;

public class OpenPdf {
    public static void openById(String id) {
        try {
            if ((new File("D:\\Bill\\" + id + ".pdf")).exists()) {
                Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler D:\\Bill\\" + id + ".pdf");
            } else {
                JOptionPane.showMessageDialog(null, "File is not Exists");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
