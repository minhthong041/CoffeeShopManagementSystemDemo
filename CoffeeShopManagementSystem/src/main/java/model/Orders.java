package model;

import java.math.BigDecimal;

public class Orders {
    private int orderId;
    private String orderTime;
    private String description;
    private BigDecimal totalAmount;
    
    private int staffId;
    private int customerId;
    private int paymentId;
    
    private String paymentMethodName;
    private String staffName;
    private String customerName;

    public Orders() {
    }

    public Orders(int orderId, String orderTime, String description, BigDecimal totalAmount, int staffId, int customerId, int paymentId, String paymentMethodName, String staffName, String customerName) {
        this.orderId = orderId;
        this.orderTime = orderTime;
        this.description = description;
        this.totalAmount = totalAmount;
        this.staffId = staffId;
        this.customerId = customerId;
        this.paymentId = paymentId;
        this.paymentMethodName = paymentMethodName;
        this.staffName = staffName;
        this.customerName = customerName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    
    
    
}
