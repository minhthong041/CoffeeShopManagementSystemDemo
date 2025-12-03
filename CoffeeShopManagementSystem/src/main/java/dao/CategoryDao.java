package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Category;

public class CategoryDao {
    public static void save(Category category){
        String query ="insert into Category (categoryName) values ('"+category.getCategoryName()+"')";
        DbOperations.setDataOrDelete(query, "Category Added Successfully!");
    }
    
    public static ArrayList<Category> getAllRecords(){
        ArrayList<Category> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getdata("select * from Category");
            while(rs.next()){
                Category category = new Category();
                category.setCategoryId(rs.getInt("categoryId"));
                category.setCategoryName(rs.getString("categoryName"));
                arrayList.add(category);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
    public static void delete(String categoryId){
        String query = "delete from category where categoryId='"+categoryId+"'";
        DbOperations.setDataOrDelete(query, "Category Deleted Successfully!");
    }
}
