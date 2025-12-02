package dao;

import model.Staff;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class StaffDao {

    public static void save(Staff staff) {
        String middleName = (staff.getMiddleName() == null || staff.getMiddleName().isEmpty())
                ? "NULL" : "N'" + staff.getMiddleName() + "'";

        String sex = (staff.getSex() == null)
                ? "NULL" : "'" + staff.getSex() + "'";

        String dob = (staff.getDateOfBirth() == null)
                ? "NULL" : "'" + staff.getDateOfBirth().toString() + "'";

        String doh = (staff.getDateOfHiring() == null)
                ? "NULL" : "'" + staff.getDateOfHiring().toString() + "'";

        String query = "INSERT INTO Staff (firstName, middleName, lastName, sex, roleId, dateOfBirth, dateOfHiring, phoneNumber, email, address, password, status) VALUES ("
                + "N'" + staff.getFirstName() + "', "
                + middleName + ", "
                + "N'" + staff.getLastName() + "', "
                + sex + ", '"
                + staff.getRoleId() + "', "
                + dob + ", "
                + doh + ", '"
                + staff.getPhoneNumber() + "', '"
                + staff.getEmail() + "', "
                + "N'" + staff.getAddress() + "', '"
                + staff.getPassword() + "', 'Inactive')";

        DbOperations.setDataOrDelete(query, "Registered Successfully! Wait for Manager Approval!");
    }

    public static Staff login(int staffId, String password) {
        Staff staff = null;
        try {
            ResultSet rs = DbOperations.getdata("select *from Staff where staffId='" + staffId + "' and password='" + password + "'");
            while (rs.next()) {
                staff = new Staff();
                staff.setStatus(rs.getString("status"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return staff;
    }

    public static void update(int id, String email, String newPassword) {
        String query = "update Staff set password = '" + newPassword + "' where staffId = " + id + " and email = '" + email + "'";
        DbOperations.setDataOrDelete(query, "Password Updated Successfully!");
    }

    public static String getRoleId(int id) {
        String roleId = "";
        try {
            ResultSet rs = DbOperations.getdata("select roleId from Staff where staffId = " + id);

            if (rs.next()) {
                roleId = rs.getString("roleId");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return roleId;
    }

    public static ArrayList<Staff> getAllRecords(String query) {
        ArrayList<Staff> list = new ArrayList<>();
        try {
            String sql = "SELECT s.staffId, s.firstName, s.middleName, s.lastName, r.roleName, s.dateOfHiring, s.password, s.status "
                    + "FROM Staff s INNER JOIN Role r ON s.roleId = r.roleId ";
            if (query != null && !query.trim().equals("")) {
                sql += "WHERE s.firstName LIKE '%" + query + "%' "
                        + "OR s.middleName LIKE '%" + query + "%' "
                        + "OR s.lastName LIKE '%" + query + "%' "
                        + "OR CAST(s.staffId AS VARCHAR) LIKE '%" + query + "%'";
            }

            ResultSet rs = DbOperations.getdata(sql);
            while (rs.next()) {
                Staff staff = new Staff();
                staff.setStaffId(rs.getInt("staffId"));
                staff.setFirstName(rs.getString("firstName"));
                staff.setMiddleName(rs.getString("middleName"));
                staff.setLastName(rs.getString("lastName"));
                staff.setRoleId(rs.getString("roleName"));
                staff.setDateOfHiring(rs.getDate("dateOfHiring").toLocalDate());
                staff.setPassword(rs.getString("password"));
                staff.setStatus(rs.getString("status"));
                list.add(staff);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }

    public static void changeStatus(int id, String status) {
        String query = "UPDATE Staff SET status = '" + status + "' WHERE staffId = " + id;
        DbOperations.setDataOrDelete(query, "Status Changed Successfully!");
    }

    public static boolean validateOldPassword(int id, String oldPassword) {
        try {
            String query = "SELECT * FROM Staff WHERE staffId = " + id + " AND password = '" + oldPassword + "'";
            ResultSet rs = DbOperations.getdata(query);
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }

    public static void updatePassword(int id, String newPassword) {
        String query = "UPDATE Staff SET password = '" + newPassword + "' WHERE staffId = " + id;
        DbOperations.setDataOrDelete(query, "Password Changed Successfully!");
    }

    public static ArrayList<model.Staff> getStaffs(String colName, String value, String sortType) {
        ArrayList<model.Staff> list = new ArrayList<>();
        try {
            String sort = "ASC";
            if (sortType != null && sortType.equals("DESC")) {
                sort = "DESC";
            }
            String whereClause = "";
            if (value != null && !value.trim().equals("")) {
                if (colName.equals("ID")) {
                    whereClause = " WHERE CAST(s.staffId AS VARCHAR) LIKE '%" + value + "%'";
                } else if (colName.equals("First Name")) {
                    whereClause = " WHERE s.firstName LIKE N'%" + value + "%'";
                } else if (colName.equals("Middle Name")) {
                    whereClause = " WHERE s.middleName LIKE N'%" + value + "%'";
                } else if (colName.equals("Last Name")) {
                    whereClause = " WHERE s.lastName LIKE N'%" + value + "%'";
                } else if (colName.equals("Role")) {
                    whereClause = " WHERE r.roleName LIKE '%" + value + "%'"; // Tìm trong bảng Role
                } else if (colName.equals("Date Of Hiring")) {
                    whereClause = " WHERE CAST(s.dateOfHiring AS VARCHAR) LIKE '%" + value + "%'";
                } else if (colName.equals("Date Of Birth")) {
                    whereClause = " WHERE CAST(s.dateOfBirth AS VARCHAR) LIKE '%" + value + "%'";
                } else if (colName.equals("Sex")) {
                    whereClause = " WHERE s.sex LIKE '%" + value + "%'";
                } else if (colName.equals("Phone Number")) {
                    whereClause = " WHERE s.phoneNumber LIKE '%" + value + "%'";
                } else if (colName.equals("Email")) {
                    whereClause = " WHERE s.email LIKE '%" + value + "%'";
                } else if (colName.equals("Address")) {
                    whereClause = " WHERE s.address LIKE N'%" + value + "%'";
                } else if (colName.equals("Status")) {
                    whereClause = " WHERE s.status LIKE '%" + value + "%'";
                }
            }
            String query = "SELECT s.*, r.roleName FROM Staff s "
                    + "INNER JOIN Role r ON s.roleId = r.roleId "
                    + whereClause
                    + " ORDER BY s.staffId " + sort;
            ResultSet rs = DbOperations.getdata(query);
            while (rs.next()) {
                Staff staff = new Staff();
                staff.setStaffId(rs.getInt("staffId"));
                staff.setFirstName(rs.getString("firstName"));
                staff.setMiddleName(rs.getString("middleName"));
                staff.setLastName(rs.getString("lastName"));
                staff.setRoleId(rs.getString("roleName"));
                staff.setDateOfHiring(rs.getDate("dateOfHiring").toLocalDate());
                java.sql.Date dob = rs.getDate("dateOfBirth");
                if (dob != null) {
                    staff.setDateOfBirth(dob.toLocalDate());
                }
                staff.setSex(rs.getString("sex"));
                staff.setPhoneNumber(rs.getString("phoneNumber"));
                staff.setEmail(rs.getString("email"));
                staff.setAddress(rs.getString("address"));
                staff.setPassword(rs.getString("password"));
                staff.setStatus(rs.getString("status"));
                list.add(staff);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }

    public static void update(Staff staff) {
        String dob = (staff.getDateOfBirth() == null) ? "NULL" : "'" + staff.getDateOfBirth().toString() + "'";
        String doh = (staff.getDateOfHiring() == null) ? "NULL" : "'" + staff.getDateOfHiring().toString() + "'";
        String query = "UPDATE Staff SET "
                + "firstName = N'" + staff.getFirstName() + "', "
                + "middleName = N'" + staff.getMiddleName() + "', "
                + "lastName = N'" + staff.getLastName() + "', "
                + "email = '" + staff.getEmail() + "', "
                + "phoneNumber = '" + staff.getPhoneNumber() + "', "
                + "address = N'" + staff.getAddress() + "', "
                + "sex = '" + staff.getSex() + "', "
                + "roleId = '" + staff.getRoleId() + "', "
                + "password = '" + staff.getPassword() + "', "
                + "status = '" + staff.getStatus() + "', "
                + "dateOfBirth = " + dob + ", "
                + "dateOfHiring = " + doh + " "
                + "WHERE staffId = " + staff.getStaffId();
        DbOperations.setDataOrDelete(query, "Staff Updated Successfully!");
    }

    public static Staff getStaffById(int id) {
        Staff staff = null;
        try {
            String query = "SELECT * FROM Staff WHERE staffId = " + id;
            ResultSet rs = DbOperations.getdata(query);
            if (rs.next()) {
                staff = new Staff();
                staff.setStaffId(rs.getInt("staffId"));
                staff.setFirstName(rs.getString("firstName"));
                staff.setMiddleName(rs.getString("middleName"));
                staff.setLastName(rs.getString("lastName"));
                staff.setPhoneNumber(rs.getString("phoneNumber"));
                staff.setEmail(rs.getString("email"));
                staff.setAddress(rs.getString("address"));
                staff.setSex(rs.getString("sex"));
                staff.setRoleId(rs.getString("roleId"));
                staff.setPassword(rs.getString("password"));
                staff.setStatus(rs.getString("status"));
                java.sql.Date sqlDob = rs.getDate("dateOfBirth");
                if (sqlDob != null) {
                    staff.setDateOfBirth(sqlDob.toLocalDate());
                }

                java.sql.Date sqlHiring = rs.getDate("dateOfHiring");
                if (sqlHiring != null) {
                    staff.setDateOfHiring(sqlHiring.toLocalDate());
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return staff;
    }
}
