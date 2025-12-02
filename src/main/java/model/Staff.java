package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Staff {
    private String lastName;
    private String middleName;
    private String firstName;
    private int staffId;
    private String password;
    private String roleId;
    private String phoneNumber;
    private String email;
    private String sex;
    private String address;
    private LocalDate dateOfBirth;
    private LocalDate dateOfHiring;
    private String status;
    private BigDecimal salaryRate;

    public Staff() {
        
    }
    
    public Staff(String lastName, String middleName, String firstName, int staffId, String password, String roleId, String phoneNumber, String email, String sex, String address, LocalDate dateOfBirth, LocalDate dateOfHiring, String status, BigDecimal salaryRate) {
        this.lastName = lastName;
        this.middleName = middleName;
        this.firstName = firstName;
        this.staffId = staffId;
        this.password = password;
        this.roleId = roleId;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.sex = sex;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.dateOfHiring = dateOfHiring;
        this.status = status;
        this.salaryRate = salaryRate;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfHiring() {
        return dateOfHiring;
    }

    public void setDateOfHiring(LocalDate dateOfHiring) {
        this.dateOfHiring = dateOfHiring;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getSalaryRate() {
        return salaryRate;
    }

    public void setSalaryRate(BigDecimal salaryRate) {
        this.salaryRate = salaryRate;
    }
    
}
