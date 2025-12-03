package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.OrderDetail;
import model.Orders;

public class OrderDao {

    public static String saveOrder(Orders order, ArrayList<OrderDetail> details) {
        String url = "jdbc:sqlserver://LOCALHOST:1433;databaseName=CoffeeShopManagement;encrypt=true;trustServerCertificate=true;";
        String user = "sa";
        String pass = "1234";

        Connection con = null;
        PreparedStatement psOrder = null;
        PreparedStatement psDetail = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(url, user, pass);
            con.setAutoCommit(false);
            String sqlOrder = "INSERT INTO Orders (totalAmount, paymentId, staffId, customerId, description) VALUES (?, ?, ?, ?, ?)";
            psOrder = con.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);
            psOrder.setBigDecimal(1, order.getTotalAmount());
            psOrder.setInt(2, order.getPaymentId());
            psOrder.setInt(3, order.getStaffId());
            psOrder.setInt(4, order.getCustomerId());
            psOrder.setString(5, order.getDescription());
            psOrder.executeUpdate();
            rs = psOrder.getGeneratedKeys();
            int genId = 0;
            if (rs.next()) {
                genId = rs.getInt(1);
            }
            String sqlDetail = "INSERT INTO OrderDetail (orderId, productId, quantity, priceAtOrder) VALUES (?, ?, ?, ?)";
            psDetail = con.prepareStatement(sqlDetail);
            for (OrderDetail detail : details) {
                psDetail.setInt(1, genId);
                psDetail.setInt(2, detail.getProductId());
                psDetail.setInt(3, detail.getQuantity());
                psDetail.setBigDecimal(4, detail.getPriceAtOrder());
                psDetail.addBatch();
            }
            psDetail.executeBatch();

            con.commit();
            return String.valueOf(genId);

        } catch (Exception e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
            }
            JOptionPane.showMessageDialog(null, e);
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (psOrder != null) {
                    psOrder.close();
                }
                if (psDetail != null) {
                    psDetail.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public static ArrayList<model.Orders> getOrdersByDateAndSort(String value, String sortType) {
        ArrayList<model.Orders> list = new java.util.ArrayList<>();
        try {
            String sort = "ASC";
            if (sortType != null && sortType.equals("DESC")) {
                sort = "DESC";
            }

            String query = "SELECT o.orderId, "
                    + "c.firstName as custFirst, c.middleName as custMid, c.lastName as custLast, "
                    + "c.phoneNumber, c.email, o.orderTime, o.totalAmount, o.staffId, "
                    + "s.firstName as staffFirst, s.middleName as staffMid, s.lastName as staffLast "
                    + "FROM Orders o "
                    + "INNER JOIN Customer c ON o.customerId = c.customerId "
                    + "INNER JOIN Staff s ON o.staffId = s.staffId "
                    + "WHERE (" +
                    "CONVERT(varchar, o.orderTime, 23) LIKE '%" + value + "%' "
                    + // -------------------------------------
                    "OR c.firstName LIKE '%" + value + "%' "
                    + "OR c.lastName LIKE '%" + value + "%' "
                    + "OR c.phoneNumber LIKE '%" + value + "%' "
                    + "OR c.email LIKE '%" + value + "%' "
                    + "OR s.firstName LIKE '%" + value + "%' "
                    + "OR s.lastName LIKE '%" + value + "%') "
                    + "ORDER BY o.orderId " + sort;

            ResultSet rs = DbOperations.getdata(query);
            while (rs.next()) {
                Orders order = new Orders();
                order.setOrderId(rs.getInt("orderId"));

                String custFirst = rs.getString("custFirst");
                String custLast = rs.getString("custLast");
                String custMid = rs.getString("custMid");
                if (custMid == null) {
                    custMid = "";
                }
                order.setCustomerName((custLast + " " + custMid + " " + custFirst).replace("  ", " "));

                String staffFirst = rs.getString("staffFirst");
                String staffLast = rs.getString("staffLast");
                String staffMid = rs.getString("staffMid");
                if (staffMid == null) {
                    staffMid = "";
                }
                order.setStaffName((staffLast + " " + staffMid + " " + staffFirst).replace("  ", " "));

                order.setDescription(rs.getString("phoneNumber") + "---" + rs.getString("email"));
                order.setOrderTime(rs.getString("orderTime"));
                order.setTotalAmount(rs.getBigDecimal("totalAmount"));
                order.setStaffId(rs.getInt("staffId"));

                list.add(order);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }
}
