package model;

public class Customer {
    private int customerId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String sex;
    private String phoneNumber;
    private String email;
    private String address;
    private int loyaltyPoint;
    
    public Customer() {
    }
    
    public Customer(int customerId, String firstName, String middleName, String lastName, String sex, String phoneNumber, String email, String address, int loyaltyPoint) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.loyaltyPoint = loyaltyPoint;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getLoyaltyPoint() {
        return loyaltyPoint;
    }

    public void setLoyaltyPoint(int loyaltyPoint) {
        this.loyaltyPoint = loyaltyPoint;
    }
    
    
}
