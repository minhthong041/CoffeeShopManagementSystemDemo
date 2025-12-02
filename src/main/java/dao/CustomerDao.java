package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Customer;

public class CustomerDao {

    public static Customer getCustomerByPhone(String phoneNumber) {
        Customer customer = null;
        try {
            ResultSet rs = DbOperations.getdata("select * from Customer where phoneNumber = '" + phoneNumber + "'");
            if (rs.next()) {
                customer = new Customer();
                customer.setCustomerId(rs.getInt("customerId"));
                customer.setFirstName(rs.getString("firstName"));
                customer.setMiddleName(rs.getString("middleName"));
                customer.setLastName(rs.getString("lastName"));
                customer.setSex(rs.getString("sex"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));
                customer.setLoyaltyPoint(rs.getInt("loyaltyPoint"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return customer;
    }

    public static void save(Customer customer) {
        String query = "insert into Customer (firstName, middleName, lastName, sex, phoneNumber, email, address, loyaltyPoint) values(N'"
                + customer.getFirstName() + "',N'" + customer.getMiddleName() + "',N'" + customer.getLastName() + "','"
                + customer.getSex() + "','" + customer.getPhoneNumber() + "','" + customer.getEmail() + "','"
                + customer.getAddress() + "'," + customer.getLoyaltyPoint() + ")";
        DbOperations.setDataOrDelete(query, "");
    }

    public static void updateLoyaltyPoints(String phoneNumber, int pointsToAdd) {
        String query = "update Customer set loyaltyPoint = loyaltyPoint + " + pointsToAdd + " where phoneNumber = '" + phoneNumber + "'";
        DbOperations.setDataOrDelete(query, "");
    }


    public static ArrayList<Customer> getCustomers(String colName, String value, String sortType) {
        ArrayList<Customer> list = new ArrayList<>();
        try {

            String sort = "ASC";
            if (sortType != null && sortType.equals("DESC")) {
                sort = "DESC";
            }
            String whereClause = "";
            if (value != null && !value.trim().equals("")) {
                if (colName.equals("ID")) {
                    whereClause = " WHERE CAST(customerId AS VARCHAR) LIKE '%" + value + "%'";
                } else if (colName.equals("First Name")) {
                    whereClause = " WHERE firstName LIKE N'%" + value + "%'";
                } else if (colName.equals("Middle Name")) {
                    whereClause = " WHERE middleName LIKE N'%" + value + "%'";
                } else if (colName.equals("Last Name")) {
                    whereClause = " WHERE lastName LIKE N'%" + value + "%'";
                } else if (colName.equals("Phone Number")) {
                    whereClause = " WHERE phoneNumber LIKE '%" + value + "%'";
                } else if (colName.equals("Email")) {
                    whereClause = " WHERE email LIKE '%" + value + "%'";
                } else if (colName.equals("Address")) {
                    whereClause = " WHERE address LIKE N'%" + value + "%'";
                } else if (colName.equals("Sex")) {
                    whereClause = " WHERE sex LIKE '%" + value + "%'";
                } else if (colName.equals("Loyalty Point")) {
                    whereClause = " WHERE CAST(loyaltyPoint AS VARCHAR) LIKE '%" + value + "%'";
                }
            }
            String query = "SELECT * FROM Customer" + whereClause + " ORDER BY customerId " + sort;
            ResultSet rs = DbOperations.getdata(query);
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customerId"));
                customer.setFirstName(rs.getString("firstName"));
                customer.setMiddleName(rs.getString("middleName"));
                customer.setLastName(rs.getString("lastName"));
                customer.setSex(rs.getString("sex"));
                customer.setPhoneNumber(rs.getString("phoneNumber"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));
                customer.setLoyaltyPoint(rs.getInt("loyaltyPoint"));
                list.add(customer);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }
}
