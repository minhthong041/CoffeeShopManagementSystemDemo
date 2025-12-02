package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.PaymentMethod;

public class PaymentMethodDao {
    public static ArrayList<PaymentMethod> getAllRecords() {
        ArrayList<PaymentMethod> list = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getdata("SELECT * FROM PaymentMethod");
            while (rs.next()) {
                PaymentMethod pm = new PaymentMethod();
                pm.setPaymentId(rs.getInt("paymentId"));
                pm.setMethodName(rs.getString("methodName"));
                list.add(pm);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }
}
