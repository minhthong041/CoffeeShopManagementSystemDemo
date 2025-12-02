package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DbOperations {

    public static void setDataOrDelete(String Query, String msg) {
        try {
            Connection con = DatabaseHelper.openConnection();
            Statement st = con.createStatement();
            st.executeUpdate(Query);
            if (!msg.equals("")) {
                JOptionPane.showMessageDialog(null, msg);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static ResultSet getdata(String query) {
        try {
            Connection con = DatabaseHelper.openConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            return rs;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
