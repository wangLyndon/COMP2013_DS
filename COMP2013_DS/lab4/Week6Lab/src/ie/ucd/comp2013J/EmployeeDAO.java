package ie.ucd.comp2013J;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public static List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<Employee>();

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JDBCTool.getConnection();
            st = conn.createStatement();

            rs = st.executeQuery("SELECT * FROM employee");

            while (rs.next()) {
                employees.add(createEmployeeWithResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCTool.closeAllConnections(conn, st, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return employees;
    }

    private static Employee createEmployeeWithResultSet(ResultSet rs) throws SQLException {
        int empno = rs.getInt("empno");
        String firstname = rs.getString("firstname");
        String familyname = rs.getString("familyname");
        String job = rs.getString("job");
        float salary = rs.getFloat("salary");
        int deptno = rs.getInt("deptno");
        return new Employee(empno, firstname, familyname, job, salary, deptno);
    }


    //TODO Question 2
    public static Employee getEmployeeByID(int eid) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JDBCTool.getConnection();
            st = conn.prepareStatement("SELECT * FROM employee WHERE empno = ?;");
            st.setInt(1, eid);
            rs = st.executeQuery();

            if (rs.next()) {
                return createEmployeeWithResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCTool.closeAllConnections(conn, st, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //TODO Question 3
    public static boolean deleteEmployeeByID(int eid) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JDBCTool.getConnection();
            st = conn.prepareStatement("DELETE FROM employee WHERE empno = ?;");
            st.setInt(1, eid);
            return st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCTool.closeAllConnections(conn, st, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    //TODO Question 4
    public static boolean updateEmployee(Employee e) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        int eid = e.getEmpno();
        String familyname = e.getFamilyname();
        String firstname = e.getFirstname();
        String job = e.getJob();
        int deptno = e.getDeptno();
        float salary = e.getSalary();

        try {
            conn = JDBCTool.getConnection();
            st = conn.prepareStatement("SELECT * FROM employee WHERE empno = ?;");
            st.setInt(1, eid);
            rs = st.executeQuery();
            if (rs.next() && employeeDataChanged(rs, firstname, familyname, job, deptno, salary)) {
                st = conn.prepareStatement("UPDATE employee SET familyname = ?, " +
                        "firstname = ?, job = ?, deptno = ?, salary = ? WHERE empno = ?;");
                st.setString(1, familyname);
                st.setString(2, firstname);
                st.setString(3, job);
                st.setInt(4, deptno);
                st.setFloat(5, salary);
                st.setInt(6, eid);
                return st.execute();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                JDBCTool.closeAllConnections(conn, st, rs);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    private static boolean employeeDataChanged(ResultSet rs, String firstName, String familyName, String job, int deptNo, float salary) throws SQLException {
        // The aim of this step is to make sure the information is different, it will save resource when they are same
        String firstnameDB = rs.getString("firstname");
        String familynameDB = rs.getString("familyname");
        String jobDB = rs.getString("job");
        int deptnoDB = rs.getInt("deptno");
        float salaryDB = rs.getFloat("salary");
        return !familynameDB.equals(familyName) || !firstnameDB.equals(firstName) || !jobDB.equals(job) || deptnoDB != deptNo || salaryDB != salary;
    }

    //TODO Question 5
    public static boolean insertEmployee(Employee e) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        int eid = e.getEmpno();
        String firstname = e.getFirstname();
        String familyname = e.getFamilyname();
        String job = e.getJob();
        float salary = e.getSalary();
        int deptno = e.getDeptno();

        try {
            conn = JDBCTool.getConnection();
            st = conn.prepareStatement("INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?);");
            st.setInt(1, eid);
            st.setString(2, firstname);
            st.setString(3, familyname);
            st.setString(4, job);
            st.setFloat(5, salary);
            st.setInt(6, deptno);
            return st.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                JDBCTool.closeAllConnections(conn, st, rs);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
}
