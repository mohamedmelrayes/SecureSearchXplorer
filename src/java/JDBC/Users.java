package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Users {

    static final String DB_URL = "jdbc:postgresql://localhost/users";

    static final String USER = "postgres";
    static final String PASS = "123";

    public Integer isUserAuth(String username, String pass) {
        Integer userID = 0;
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName("org.postgresql.Driver");
            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a querybookName
            stmt = conn.createStatement();
            String sql = "select * from udata where username ='" + username + "' AND password='" + pass + "';";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                userID = rs.getInt("id");
            }

            //STEP 4: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {

            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try

        return userID;

    }

    public boolean addNewUser(String firstName, String lastName, String username, String password) {
        boolean res = false;
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a querybookName
            stmt = conn.createStatement();
            String sql = "INSERT INTO udata (fname, lname, username, password) values ('" + firstName + "','" + lastName + "','" + username + "','" + password + "');";
            int numberOfRowEffected = stmt.executeUpdate(sql);

            if (numberOfRowEffected == 1) {
                res = true;
            }

            //STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {

            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try

        return res;

    }

    public UserInfo getUserInfo(String username) {
        UserInfo userInfo = new UserInfo();
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a querybookName
            stmt = conn.createStatement();
            String sql = "select * from udata where username='" + username + "';";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                
                userInfo.setFname(rs.getString("fname"));
                userInfo.setLname(rs.getString("lname"));
                userInfo.setPassword(rs.getString("password"));
                userInfo.setUsername(username);

            }

            //STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {

            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try

        return userInfo;
    }
    
    public List<UserInfo> getAllUserInfo() {
        List<UserInfo> userInfo =(List<UserInfo>) new ArrayList<UserInfo>();
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a querybookName
            stmt = conn.createStatement();
            String sql = "select * from udata ;";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                UserInfo user = new UserInfo();
                user.setFname(rs.getString("fname"));
                user.setLname(rs.getString("lname"));
                user.setPassword(rs.getString("password"));
                user.setUsername(rs.getString("username"));
                userInfo.add(user);
               
            }

            //STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {

            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try

        return userInfo;
    }
}
