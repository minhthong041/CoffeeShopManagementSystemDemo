package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Product;

public class ProductDao {

    public static void save(Product product) {
        String query = "INSERT INTO Product (productName, categoryId, price, status) VALUES('"
                + product.getProductName() + "',"
                + product.getCategoryId() + ","
                + product.getPrice() + ",'Active')";
        DbOperations.setDataOrDelete(query, "Product Added Successfully");
    }

    public static ArrayList<Product> getAllRecords() {
        java.util.ArrayList<Product> list = new ArrayList<>();
        try {
            String query = "SELECT p.productId, p.productName, p.price, p.status, c.categoryName "
                    + "FROM Product p INNER JOIN Category c ON p.categoryId = c.categoryId";

            ResultSet rs = DbOperations.getdata(query);
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setStatus(rs.getString("status"));
                product.setCategory(rs.getString("categoryName"));
                list.add(product);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }

    public static ArrayList<Product> getRecordsByCategory(int categoryId) {
        ArrayList<Product> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM Product WHERE categoryId = " + categoryId + " AND status = 'Active'";

            ResultSet rs = DbOperations.getdata(query);
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                product.setCategoryId(rs.getInt("categoryId"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setStatus(rs.getString("status"));
                list.add(product);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }

    public static void updateStatus(int id, String status) {
        String query = "UPDATE Product SET status = '" + status + "' WHERE productId = " + id;
        DbOperations.setDataOrDelete(query, "Status Updated Successfully!");
    }

    public static void update(Product product) {
        String query = "UPDATE Product SET productName = '" + product.getProductName() + "', "
                + "categoryId = " + product.getCategoryId() + ", "
                + "price = " + product.getPrice() + " "
                + "WHERE productId = " + product.getProductId();

        DbOperations.setDataOrDelete(query, "Product Updated Successfully!");
    }

    public static void delete(int id) {
        String query = "DELETE FROM Product WHERE productId = " + id;
        DbOperations.setDataOrDelete(query, "Product Deleted Successfully!");
    }

    public static ArrayList<Product> filterProductByName(String name, String categoryId) {
        ArrayList<Product> list = new ArrayList<>();
        try {
            String query = "SELECT p.productId, p.productName, p.price, p.status, c.categoryName "
                    + "FROM Product p INNER JOIN Category c ON p.categoryId = c.categoryId "
                    + "WHERE p.status = 'Active' AND p.productName LIKE '%" + name + "%'";
            if (categoryId != null && !categoryId.equals("All")) {
                query += " AND p.categoryId = '" + categoryId + "'";
            }
            ResultSet rs = DbOperations.getdata(query);
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setStatus(rs.getString("status"));
                product.setCategory(rs.getString("categoryName"));
                list.add(product);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }

    public static Product getProductByName(String name) {
        Product product = null;
        try {
            String query = "SELECT * FROM Product WHERE productName = '" + name + "' AND status = 'Active'";
            ResultSet rs = DbOperations.getdata(query);
            if (rs.next()) {
                product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setCategoryId(rs.getInt("categoryId"));
                product.setStatus(rs.getString("status"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return product;
    }
}
