package model;

import java.math.BigDecimal;

public class Product {
    private int productId;
    private String productName;
    private int categoryId;
    private BigDecimal price;
    private String category;
    private String status;
    
    public Product() {
    }
    
    public Product(int productId, String productName, int categoryId, BigDecimal price, String category, String status) {
        this.productId = productId;
        this.productName = productName;
        this.categoryId = categoryId;
        this.price = price;
        this.category = category;
        this.status = status;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
